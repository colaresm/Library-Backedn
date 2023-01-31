package com.library.libraryWDA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationListItemResponse;
import com.library.libraryWDA.model.AdditionalPatientInformation;
import com.library.libraryWDA.model.Patient;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface AdditionalPatientInformationRepository extends JpaRepository<AdditionalPatientInformation, UUID>{
    @Query("SELECT s from AdditionalPatientInformation s WHERE s.patient=:patientId")
    List<AdditionalPatientInformationListItemResponse> findAllByPatientId(Patient patientId); 
}