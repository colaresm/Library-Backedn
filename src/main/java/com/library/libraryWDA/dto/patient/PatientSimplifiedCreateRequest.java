package com.library.libraryWDA.dto.patient;

import com.library.libraryWDA.dto.BasePersonRequest;
import com.library.libraryWDA.dto.address.AddressSimplifiedRequest;
import com.library.libraryWDA.validation.constraint.FutureDateConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientSimplifiedCreateRequest {
    @NotNull(message = "O campo data de nascimento não deve ser nulo")
    @FutureDateConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "O campo nome não deve estar em branco")
    @Size(max = 60, message = "O tamanho do campo nome deve ser entre {min} e {max}")
    private String name;

    @CPF
    private String cpf;

    @NotBlank(message = "O campo número de telefone não deve estar em branco")
    @Pattern(regexp = "^[0-9]{11}$", message = "Esse campo aceita somente 11 números")
    private String firstPhone;

    
    @Valid
    private AddressSimplifiedRequest addressRequest;
}
