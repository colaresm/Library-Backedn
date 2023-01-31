package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.password.ResetPasswordCodeConfirmRequest;
import com.library.libraryWDA.dto.password.ResetPasswordEmailRequest;
import com.library.libraryWDA.dto.password.ResetPasswordUpdateRequest;
import com.library.libraryWDA.exception.user.UserNotFoundException;
import com.library.libraryWDA.model.PasswordResetToken;
import com.library.libraryWDA.model.User;
import com.library.libraryWDA.repository.PasswordResetTokenRepository;
import com.library.libraryWDA.repository.UserRepository;
import com.library.libraryWDA.service.ResetPasswordService;
import com.library.libraryWDA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
@Transactional
public class ResetPasswordServiceImpl implements ResetPasswordService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private JavaMailSender javaMailSender;

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public ResetPasswordServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository, JavaMailSender javaMailSender, UserRepository userRepository, UserService userService) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
        this.userService = userService;
    }
    public boolean sendEmailCode(ResetPasswordEmailRequest resetPasswordEmailRequest) throws MessagingException {
        PasswordResetToken passwordResetTokenExists = passwordResetTokenRepository.findByEmailAndConfirmedOrChanged(resetPasswordEmailRequest.getEmail(), false, false);
        if ( userRepository.existsByUsernameIgnoreCase(resetPasswordEmailRequest.getEmail())) {
            if(passwordResetTokenExists != null && passwordResetTokenExists.getDateExpiration().isAfter(LocalDateTime.now()) ){
                passwordResetTokenExists.setConfirmed(true);
                passwordResetTokenExists.setChanged(true);
                passwordResetTokenRepository.save(passwordResetTokenExists);
            }
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setEmail(resetPasswordEmailRequest.getEmail());
            long number = (long) (100000 + Math.random() * 999999);
            passwordResetToken.setCode(number);
            int addMinuteTime = 720;
            passwordResetToken.setDateExpiration(LocalDateTime.now().plusMinutes(addMinuteTime));
            passwordResetToken.setConfirmed(false);
            passwordResetToken.setChanged(false);
            passwordResetTokenRepository.save(passwordResetToken);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("Recuperação de senha libraryWDA");
            helper.setTo(resetPasswordEmailRequest.getEmail());
            helper.setText("O seu código de recuperação é: " + number + ". Insira no local indicado no site.", true);
            javaMailSender.send(message);
            return true;

        } else {
            return false;
        }

    }
    @Override
    public boolean recoveryPassword(ResetPasswordCodeConfirmRequest resetPasswordCodeConfirmRequest) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByCodeAndEmail(resetPasswordCodeConfirmRequest.getCode(), resetPasswordCodeConfirmRequest.getEmail());
        if (passwordResetToken != null && !passwordResetToken.getChanged() && passwordResetToken.getDateExpiration().isAfter(LocalDateTime.now())){
            passwordResetToken.setConfirmed(true);
            passwordResetTokenRepository.save(passwordResetToken);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean passwordUpdate(ResetPasswordUpdateRequest resetPasswordUpdateRequest) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByCodeAndEmail(resetPasswordUpdateRequest.getCode(), resetPasswordUpdateRequest.getEmail());
        if (passwordResetToken.getConfirmed() && !passwordResetToken.getChanged() && passwordResetToken.getDateExpiration().isAfter(LocalDateTime.now())) {
            User user = (User) userRepository.findByUsernameIgnoreCase(resetPasswordUpdateRequest.getEmail()).orElseThrow(() -> new UserNotFoundException("User Not Found with email: " + resetPasswordUpdateRequest.getEmail()));
            passwordResetToken.setChanged(true);
            passwordResetTokenRepository.save(passwordResetToken);
            userService.updatePassword(user, resetPasswordUpdateRequest.getNewPassword());
            return true;
        } else {
            return false;
        }

    }
}
