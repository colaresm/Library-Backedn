package com.library.libraryWDA.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientCreateRequest;
import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientListItemResponse;
import com.library.libraryWDA.mapper.ClinicalHistoryPatientMapper;
import com.library.libraryWDA.model.ClinicalHistoryPatient;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.repository.ClinicalHistoryPatientRepository;
import com.library.libraryWDA.service.ClinicalHistoryPatientService;
import com.library.libraryWDA.service.PatientService;

import java.util.List;
import java.util.UUID;

@Service
public class ClinicalHistoryPatientServiceImpl implements ClinicalHistoryPatientService{
    private final ClinicalHistoryPatientMapper clinicalHistoryPatientMapper;
    private final ClinicalHistoryPatientRepository clinicalHistoryPatientRepository;
    private final PatientService patientService;


    @Autowired
    public ClinicalHistoryPatientServiceImpl(ClinicalHistoryPatientMapper clinicalHistoryPatientMapper,ClinicalHistoryPatientRepository clinicalHistoryPatientRepository, PatientService patientService) {
        this.clinicalHistoryPatientMapper = clinicalHistoryPatientMapper;
        this.clinicalHistoryPatientRepository = clinicalHistoryPatientRepository;
        this.patientService=patientService;
     
    }
    public void create(ClinicalHistoryPatientCreateRequest clinicalHistoryPatientCreateRequest) {
        ClinicalHistoryPatient clinicalHistoryPatientToCreate = clinicalHistoryPatientMapper.toModel(clinicalHistoryPatientCreateRequest);
        Patient patientFound = patientService.getById(clinicalHistoryPatientCreateRequest.getPatientId());
        clinicalHistoryPatientToCreate.setPatient(patientFound);
        clinicalHistoryPatientRepository.save(clinicalHistoryPatientToCreate);
    }
    @Override
    public List<ClinicalHistoryPatientListItemResponse> findAllByPatientId(Patient patientId) {
        return clinicalHistoryPatientRepository.findAllByPatientId(patientId);
    }
}
