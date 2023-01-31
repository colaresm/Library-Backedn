package com.library.libraryWDA.dto.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordCodeConfirmRequest {

    @NotBlank(message = "O campo código não deve estar em branco")
    private Long code;

    @NotBlank(message = "O campo e-mail não deve estar em branco")
    @Email
    private String email;
}
