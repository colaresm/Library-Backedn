package com.library.libraryWDA.dto.client;

import com.library.libraryWDA.dto.BasePersonRequest;
import com.library.libraryWDA.dto.user.UserCreateRequest;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.validation.constraint.FutureDateConstraint;
import com.library.libraryWDA.validation.constraint.OfAgeConstraint;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateRequest extends BasePersonRequest {

    @NotNull(message = "O campo data de nascimento não deve ser nulo")
    @OfAgeConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "O campo admissão não deve ser nulo")
    @FutureDateConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate admission;

    @Pattern(regexp = "^$|(^[0-9]{6}/(?:AC|AL|AP|AM|BA|CE|DF|GO|ES|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SP|SC|SE|TO))$", message = "CRM inválido.")
    private String crm;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo cargo não deve ser nulo")
    private Position position;

    @Valid
    private UserCreateRequest userCreateRequest;
}