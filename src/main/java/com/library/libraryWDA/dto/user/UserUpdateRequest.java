package com.library.libraryWDA.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotNull(message = "O campo id não deve ser nulo")
    private UUID id;

    @Email
    private String username;

    @NotBlank(message = "O campo senha não deve estar em branco")
    @Size(min = 6, max = 40, message = "O tamanho do campo senha deve ser entre {min} e {max}")
    private String password;
}
