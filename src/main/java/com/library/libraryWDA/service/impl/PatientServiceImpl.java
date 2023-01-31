package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.patient.PatientCreateRequest;
import com.library.libraryWDA.dto.patient.PatientListItemResponse;
import com.library.libraryWDA.dto.patient.PatientReminderListItemResponse;
import com.library.libraryWDA.dto.patient.PatientSimplifiedCreateRequest;
import com.library.libraryWDA.dto.patient.PatientUpdateRequest;
import com.library.libraryWDA.exception.patient.PatientNotFoundException;
import com.library.libraryWDA.mapper.PatientMapper;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.repository.PatientRepository;
import com.library.libraryWDA.service.PatientService;
import com.library.libraryWDA.validation.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientValidator patientValidator;
    private final PatientMapper patientMapper;


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientValidator patientValidator, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientValidator = patientValidator;
        this.patientMapper = patientMapper;
    }

    @Override
    public void create(PatientCreateRequest patientCreateRequest){
        patientValidator.validateForCreation(patientCreateRequest);

        Patient patientToCreate = patientMapper.toModel(patientCreateRequest);
        patientRepository.save(patientToCreate);
    }
    @Override
    public void createSimplified(PatientSimplifiedCreateRequest patientSimplifiedCreateRequest){
      patientValidator.validateForCreationSimplified(patientSimplifiedCreateRequest);

        Patient patientToCreate = patientMapper.toModelSimplified(patientSimplifiedCreateRequest);
        patientRepository.save(patientToCreate);
    }

    public Page<PatientListItemResponse> findAll(String status, Pageable pageable) {
        if(status != null) {
            return patientRepository.findAllByIsActive(status.equals("active"), pageable).map(patientMapper::toPatientListItemResponse);
        } else {
            return patientRepository.findAll(pageable).map(patientMapper::toPatientListItemResponse);
        }
    }

    @Override
    public void updateToDeactivated(UUID id) {
        Patient patientToUpdate = getById(id);
        patientToUpdate.setIsActive(false);
        patientRepository.save(patientToUpdate);
    }

    @Override
    public void updateToActivated(UUID id) {
        Patient patientToUpdate = getById(id);
        patientToUpdate.setIsActive(true);

        patientRepository.save(patientToUpdate);
    }
    public void update(PatientUpdateRequest patientUpdateRequest){
        Patient foundPatient = getById(patientUpdateRequest.getId());

        patientValidator.validateForUpdate(foundPatient, patientUpdateRequest);

        Patient patientToUpdate = patientMapper.toModel(patientUpdateRequest);
        patientToUpdate.setCreatedAt(foundPatient.getCreatedAt());
        patientToUpdate.setIsActive(foundPatient.getIsActive());
        patientToUpdate.getAddress().setId(foundPatient.getAddress().getId());
        patientToUpdate.getAddress().setCreatedAt(foundPatient.getCreatedAt());

        patientRepository.save(patientToUpdate);
    }

    @Override
    public Patient getById(UUID id){
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }

    public Patient getByEmail(String email){
        return patientRepository.findByEmail(email).orElseThrow(() -> new PatientNotFoundException(email));
    }

    public Page<PatientReminderListItemResponse> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable).map(patientMapper::toPatientReminderListItemResponse);
    }

    @Override
    public List<PatientListItemResponse> findAllActive() {
        return patientRepository.findAllByIsActiveTrue().stream().map(patientMapper::toPatientListItemResponse).collect(Collectors.toList());
    }
}