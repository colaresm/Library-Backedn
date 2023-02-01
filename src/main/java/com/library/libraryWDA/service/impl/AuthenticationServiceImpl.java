package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.User;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.service.AuthenticationService;
import com.library.libraryWDA.service.ClientService;
import com.library.libraryWDA.service.UserService;
import com.library.libraryWDA.utils.JwtTokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenManager jwtTokenManager;

    private final UserDetailsService userDetailsService;

    private final UserService userService;

    private final ClientService professionalService;

    @Autowired
    public AuthenticationServiceImpl(JwtTokenManager jwtTokenManager, UserDetailsService userDetailsService,
                                     UserService userService, ClientService professionalService) {
        this.jwtTokenManager = jwtTokenManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.professionalService = professionalService;
    }


    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(jwtTokenManager.isTokenPresent(request.getHeader(AUTHORIZATION))){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                String username = jwtTokenManager.getUsernameFromToken(refresh_token);
                UserDetails user = userDetailsService.loadUserByUsername(username);
                String access_token = jwtTokenManager.generateToken(user, request.getRequestURL().toString());
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                jwtTokenManager.catchExceptionToken(response, exception);
            }
        }else{
            throw new RuntimeException ("Refresh token is missing");
        }
    }

    @Override
    public Map<String, String> payload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        Map<String, String> userPayload = new HashMap<>();

        if(jwtTokenManager.isTokenPresent(authorizationHeader)){
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                String username = jwtTokenManager.getUsernameFromToken(token);
                User user = userService.getUser(username);
                Client professional = professionalService.geClientByUser(user);

                userPayload.put("username", user.getUsername());
                userPayload.put("role", user.getRoles().stream().findFirst().get().getName());
                userPayload.put("name", professional.getName());
                userPayload.put("professionalId", professional.getId().toString());

                return userPayload;
            }catch (Exception exception){
               jwtTokenManager.catchExceptionToken(response, exception);
            }
        }else{
            throw new RuntimeException("Token is missing");
        }
        return userPayload;
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Boolean isProfessionalLoggedWithPosition(Position position) {
        return getAuthentication().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(position.getRole()::equals);
    }

    public UUID getProfessionalIdLogged() {
        User userFound = userService.getUser(getAuthentication().getName());
        Client professionalFound = professionalService.geClientByUser(userFound);
        return professionalFound.getId();
    }

    public Client getProfessionalLogged() {
        User userFound = userService.getUser(getAuthentication().getName());
        return professionalService.geClientByUser(userFound);
    }
}
