package com.library.libraryWDA.controller.docs;

import com.library.libraryWDA.dto.password.ResetPasswordCodeConfirmRequest;
import com.library.libraryWDA.dto.password.ResetPasswordEmailRequest;
import com.library.libraryWDA.dto.password.ResetPasswordUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

@Api("Recovery password management")
public interface AuthenticationControllerDocs {

    @ApiOperation(value = "Login", notes = "Login with the given credentials.")
    void login(String username, String password);

    @ApiOperation(value = "Envia um email com o código para recuperação de senha")
    ResponseEntity<String> sendEmailWithCode(ResetPasswordEmailRequest resetPasswordEmailRequest) throws MessagingException;

    @ApiOperation(value = "Requisição com o código de recuperação de senha")
    ResponseEntity<String> recoverPassword(ResetPasswordCodeConfirmRequest resetPasswordCodeConfirmRequest);

    @ApiOperation(value = "Requisição de mudança de senha")
    ResponseEntity<String> passwordUpdate(ResetPasswordUpdateRequest resetPasswordUpdateRequest);
}