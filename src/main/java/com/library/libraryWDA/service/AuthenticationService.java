package com.library.libraryWDA.service;

import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.enums.Position;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public interface AuthenticationService {

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    Map<String, String> payload(HttpServletRequest request, HttpServletResponse response) throws IOException;

    Authentication getAuthentication();

    Boolean isProfessionalLoggedWithPosition(Position position);

    UUID getProfessionalIdLogged();

    Client getProfessionalLogged();
}
