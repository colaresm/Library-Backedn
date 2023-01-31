package com.library.libraryWDA.dto.patient;

import com.library.libraryWDA.dto.address.AddressResponse;
import com.library.libraryWDA.model.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {

    private UUID id;

    private String name;

    private String cpf;

    private String email;

    private String firstPhone;

    private String secondPhone;

    private String rg;

    private Document profilePicture;

    private LocalDate birthDate;

    private String emergencyPhone;

    private AddressResponse addressResponse;
}