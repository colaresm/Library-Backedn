package com.library.libraryWDA.service;

import java.util.List;


import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientCreateRequest;
import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientListItemResponse;
import com.library.libraryWDA.model.Patient;
public interface ClinicalHistoryPatientService {
    void create(ClinicalHistoryPatientCreateRequest clinicalHistoryPatientCreateRequest);
    List<ClinicalHistoryPatientListItemResponse> findAllByPatientId(Patient patientId);
}
