package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.AuthenticationControllerDocs;
import com.library.libraryWDA.dto.password.ResetPasswordCodeConfirmRequest;
import com.library.libraryWDA.dto.password.ResetPasswordEmailRequest;
import com.library.libraryWDA.dto.password.ResetPasswordUpdateRequest;
import com.library.libraryWDA.service.AuthenticationService;
import com.library.libraryWDA.service.ResetPasswordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController implements AuthenticationControllerDocs {


    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        throw new IllegalStateException("Adicionar Spring Security para tratar a autenticação");
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    @GetMapping("/token/payload")
    public void payload(HttpServletRequest request, HttpServletResponse response) throws IOException {
         Map<String, String> tokenPayload = authenticationService.payload(request, response);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokenPayload);
    }


    @PostMapping("/recover-password")
    public ResponseEntity<String> sendEmailWithCode(@RequestBody ResetPasswordEmailRequest resetPasswordEmailRequest) throws MessagingException {
        if (resetPasswordService.sendEmailCode(resetPasswordEmailRequest)) {
            return new ResponseEntity<>("Código enviado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email inexistente!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/recover-password/confirm")
    public ResponseEntity<String> recoverPassword(@RequestBody ResetPasswordCodeConfirmRequest resetPasswordCodeConfirmRequest) {
        if (resetPasswordService.recoveryPassword(resetPasswordCodeConfirmRequest)) {
            return new ResponseEntity<>("Código confirmado, mude sua senha!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Código inválido ou expirado", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/recover-password/change-password")
    public ResponseEntity<String> passwordUpdate(@RequestBody ResetPasswordUpdateRequest resetPasswordUpdateRequest) {
        if (resetPasswordService.passwordUpdate(resetPasswordUpdateRequest)) {
            return new ResponseEntity<>("Mudança de senha realizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Não foi possível realizar a troca da senha, tente novamente!", HttpStatus.BAD_REQUEST);
        }
    }
}