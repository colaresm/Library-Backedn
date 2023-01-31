package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.patient.PatientCreateRequest;
import com.library.libraryWDA.dto.patient.PatientListItemResponse;
import com.library.libraryWDA.dto.patient.PatientReminderListItemResponse;
import com.library.libraryWDA.dto.patient.PatientSimplifiedCreateRequest;
import com.library.libraryWDA.dto.patient.PatientUpdateRequest;
import com.library.libraryWDA.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface PatientService {

    void create(PatientCreateRequest patientCreateRequest);

    void createSimplified(PatientSimplifiedCreateRequest patientSimplifiedCreateRequest);

    Page<PatientListItemResponse> findAll(String status, Pageable pageable);

    void updateToDeactivated(UUID id);

    void updateToActivated(UUID id);

    Patient getById(UUID id);

    void update(PatientUpdateRequest patientUpdateRequest);

    Patient getByEmail(String email);

    Page<PatientReminderListItemResponse> findAll(Pageable pageable);

    List<PatientListItemResponse> findAllActive();
}