package com.library.libraryWDA.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class JwtTokenManager {

    private final String secret;

    private final Long tokenValidity;

    private final Long refreshTokenValidity;

    public JwtTokenManager(@Value("${jwt.secret}") String secret,
                           @Value("${jwt.validity.token}") Long tokenValidity,
                           @Value("${jwt.validity.refresh-token}") Long refreshTokenValidity) {
        this.secret = secret;
        this.tokenValidity = tokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String generateToken(UserDetails userDetails, String issuer) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenValidity * 60 * 60 * 1000))
                .withIssuer(issuer)
                .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    public String generateRefreshToken(UserDetails userDetails, String issuer) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenValidity * 60 * 60 * 1000))
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    public String getUsernameFromToken(String token) {
        return decoderJWT(token).getSubject();
    }

    public Collection<SimpleGrantedAuthority> getRolesFromToken(String token) {
        String[] roles = decoderJWT(token).getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        return authorities;
    }

    public Date getExpirationDateFromToken(String token) {
        return decoderJWT(token).getExpiresAt();
    }

    private DecodedJWT decoderJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public boolean isTokenPresent(String requestTokenHeader) {
        return requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ");
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public void catchExceptionToken(HttpServletResponse response, Exception exception) throws IOException {
        response.setHeader("error", exception.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        Map<String, String> error = new HashMap<>();
        error.put("error_message", exception.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
