package com.library.libraryWDA.dto.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordEmailRequest {

    @NotBlank(message = "O campo e-mail não deve estar em branco")
    @Email
    private String email;
}

