package com.library.libraryWDA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderMultipleRequest {

    @Email
    @NotEmpty
    ArrayList<String> email;

    @NotBlank(message = "O corpo da mensagem não deve estar em branco")
    @Size(min = 5, message = "O tamanho do corpo da mensagem deve ser entre {min} e {max}")
    String body;

}