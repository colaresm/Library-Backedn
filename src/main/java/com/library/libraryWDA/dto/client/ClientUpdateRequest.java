package com.library.libraryWDA.dto.client;

import com.library.libraryWDA.dto.BasePersonRequest;
import com.library.libraryWDA.dto.user.UserUpdateRequest;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.validation.constraint.FutureDateConstraint;
import com.library.libraryWDA.validation.constraint.OfAgeConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateRequest extends BasePersonRequest {

    @NotNull(message = "O campo id não deve ser nulo")
    private UUID id;

    @NotNull(message = "O campo data de nascimento não deve ser nulo")
    @OfAgeConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo tipo de usuário não deve ser nulo")
    private Position position;

    @Valid
    private UserUpdateRequest userUpdateRequest;
}
