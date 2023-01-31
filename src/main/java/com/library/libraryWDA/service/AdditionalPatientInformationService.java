package com.library.libraryWDA.service;

import java.util.List;

import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationCreateRequest;
import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationListItemResponse;
import com.library.libraryWDA.model.Patient;

public interface AdditionalPatientInformationService {
    void create(AdditionalPatientInformationCreateRequest additionalPatientInformationCreateRequest);
    List<AdditionalPatientInformationListItemResponse> findAllByPatientId(Patient patientId);
}
