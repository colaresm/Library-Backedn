package com.library.libraryWDA.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientListItemResponse {

    private UUID id;

    private String name;

    private String email;

    private String firstPhone;

    private String emergencyPhone;

    private Boolean isActive;

    private PatientAddressListItemResponse patientAddressListItemResponse;

}
