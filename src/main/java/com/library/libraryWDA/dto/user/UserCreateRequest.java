package com.library.libraryWDA.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @Email
    private String username;

    @NotBlank(message = "O campo senha não deve estar em branco")
    @Size(min = 6, max = 40, message = "O tamanho do campo senha deve ser entre {min} e {max}")
    private String password;
}
