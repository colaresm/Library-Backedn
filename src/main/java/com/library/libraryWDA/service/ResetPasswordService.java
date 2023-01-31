package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.password.ResetPasswordCodeConfirmRequest;
import com.library.libraryWDA.dto.password.ResetPasswordEmailRequest;
import com.library.libraryWDA.dto.password.ResetPasswordUpdateRequest;

import javax.mail.MessagingException;

public interface ResetPasswordService {
    boolean sendEmailCode(ResetPasswordEmailRequest resetPasswordEmailRequest) throws MessagingException;
    boolean recoveryPassword(ResetPasswordCodeConfirmRequest resetPasswordCodeConfirmRequest);
    boolean passwordUpdate(ResetPasswordUpdateRequest resetPasswordUpdateRequest);
}
