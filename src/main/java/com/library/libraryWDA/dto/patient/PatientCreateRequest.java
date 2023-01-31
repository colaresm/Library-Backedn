package com.library.libraryWDA.dto.patient;

import com.library.libraryWDA.dto.BasePersonRequest;
import com.library.libraryWDA.validation.constraint.FutureDateConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientCreateRequest extends BasePersonRequest {

    @NotNull(message = "O campo data de nascimento não deve ser nulo")
    @FutureDateConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

   
    @Pattern(regexp =  "^$|(^[0-9]{11})$", message = "O campo tel. de emergência aceita somente 11 números")
    private String emergencyPhone;
}