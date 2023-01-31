package com.library.libraryWDA.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
 

import java.util.UUID;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class ExamUpdateRequest {

    @NotNull(message = "O campo id não deve ser nulo")
    private UUID id;

    @NotBlank(message = "O campo código não deve estar em branco")
    @Pattern(regexp = "^[0-9]+$", message = "O campo código só aceita números")
    private String code;

    @NotBlank(message = "O campo nome não deve estar em branco")
    @Size(min = 3, max = 50, message = "O tamanho do campo nome deve ser entre {min} e {max}")
    private String name;
}
