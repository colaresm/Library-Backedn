package com.library.libraryWDA.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationCreateRequest;
import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationListItemResponse;
import com.library.libraryWDA.mapper.AdditionalPatientInformationMapper;
import com.library.libraryWDA.model.AdditionalPatientInformation;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.repository.AdditionalPatientInformationRepository;
import com.library.libraryWDA.service.AdditionalPatientInformationService;
import com.library.libraryWDA.service.PatientService;
 
@Service
public class AdditionalPatientInformationServiceImpl  implements AdditionalPatientInformationService{
    private final AdditionalPatientInformationMapper additionalPatientInformationMapper;
    private final AdditionalPatientInformationRepository additionalPatientInformationRepository;
    private final PatientService patientService;

    public AdditionalPatientInformationServiceImpl(AdditionalPatientInformationMapper additionalPatientInformationMapper,AdditionalPatientInformationRepository additionalPatientInformationRepository,PatientService patientService) {
        this.additionalPatientInformationMapper = additionalPatientInformationMapper;
        this.additionalPatientInformationRepository = additionalPatientInformationRepository;
        this.patientService=patientService;
     
    }
    public void create(AdditionalPatientInformationCreateRequest additionalPatientInformationCreateRequest) {
        AdditionalPatientInformation additionalPatientInformationToCreate = additionalPatientInformationMapper.toModel(additionalPatientInformationCreateRequest);
        Patient patientFound = patientService.getById(additionalPatientInformationCreateRequest.getPatientId());
        additionalPatientInformationToCreate.setPatient(patientFound);
        additionalPatientInformationRepository.save(additionalPatientInformationToCreate);
    }

    @Override
    public List<AdditionalPatientInformationListItemResponse> findAllByPatientId(Patient patientId) {
        return additionalPatientInformationRepository.findAllByPatientId(patientId);
    }
}