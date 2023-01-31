package com.library.libraryWDA.repository;


import java.util.List;
import java.util.UUID;

import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientListItemResponse;
import com.library.libraryWDA.model.ClinicalHistoryPatient;
import com.library.libraryWDA.model.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalHistoryPatientRepository  extends JpaRepository<ClinicalHistoryPatient, UUID>{
    @Query("SELECT s from ClinicalHistoryPatient s WHERE s.patient=:patientId")
    List<ClinicalHistoryPatientListItemResponse> findAllByPatientId(Patient patientId); 
}
