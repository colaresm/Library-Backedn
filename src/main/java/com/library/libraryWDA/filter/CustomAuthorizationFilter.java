package com.library.libraryWDA.filter;

import com.library.libraryWDA.utils.JwtTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(verifyRequestPath(request)) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(jwtTokenManager.isTokenPresent(authorizationHeader)) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    String username = jwtTokenManager.getUsernameFromToken(token);
                    Collection<SimpleGrantedAuthority> authorities = jwtTokenManager.getRolesFromToken(token);
                    addUsernameInContext(username, token, authorities);
                    filterChain.doFilter(request, response);
                } catch (Exception exception) {
                    jwtTokenManager.catchExceptionToken(response, exception);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private boolean verifyRequestPath(HttpServletRequest request) {
        return request.getServletPath().equals("/authentication/**");
    }

    private void addUsernameInContext(String username, String token,Collection<SimpleGrantedAuthority> authorities) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(jwtTokenManager.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
