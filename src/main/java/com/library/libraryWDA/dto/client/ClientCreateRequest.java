package com.library.libraryWDA.dto.client;

import com.library.libraryWDA.dto.BasePersonRequest;
import com.library.libraryWDA.dto.user.UserCreateRequest;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.validation.constraint.OfAgeConstraint;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateRequest extends BasePersonRequest {

    @NotNull(message = "O campo data de nascimento não deve ser nulo")
    @OfAgeConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo tipo de usuário")
    private Position position;

    @Valid
    private UserCreateRequest userCreateRequest;
}