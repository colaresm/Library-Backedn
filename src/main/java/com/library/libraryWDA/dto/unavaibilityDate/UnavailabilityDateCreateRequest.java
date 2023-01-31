package com.library.libraryWDA.dto.unavaibilityDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnavailabilityDateCreateRequest {

    @NotBlank(message = "O campo nome não deve estar em branco")
    @Size(max = 50, message = "O tamanho do campo nome deve ser entre {min} e {max}")
    private String name;

    @NotNull(message = "O campo data inicial não deve ser nulo")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate initialDate;

    @NotNull(message = "O campo data final não deve ser nulo")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate finalDate;
}
