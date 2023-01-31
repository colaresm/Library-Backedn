package com.library.libraryWDA.validation;

import com.library.libraryWDA.dto.patient.PatientCreateRequest;
import com.library.libraryWDA.dto.patient.PatientSimplifiedCreateRequest;
import com.library.libraryWDA.dto.patient.PatientUpdateRequest;
import com.library.libraryWDA.exception.patient.PatientAlreadyExistsException;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@SuppressWarnings("unused")

@Component
public class PatientValidator {

    @Autowired
    private PatientRepository patientRepository;

    public void validateForCreation(PatientCreateRequest patientCreateRequest) {
        validateCPF(patientCreateRequest.getCpf());
        validateRG(patientCreateRequest.getRg());
        validateEmail(patientCreateRequest.getEmail());
    }
public void validateForCreationSimplified(PatientSimplifiedCreateRequest patientSimplifiedCreateRequest){

    validateCPF(patientSimplifiedCreateRequest.getCpf());
   
}
    public void validateForUpdate(Patient foundPatient, PatientUpdateRequest patientUpdateRequest) {
        validateCpfUpdate(foundPatient.getCpf(), patientUpdateRequest.getCpf());
        validateRgUpdate(foundPatient.getRg(), patientUpdateRequest.getRg());
        validateEmailUpdate(foundPatient.getEmail(), patientUpdateRequest.getEmail());
    }

    private void validateCPF(String CPF) {
        patientRepository.findByCpf(CPF).ifPresent(patient -> {
            throw new PatientAlreadyExistsException("CPF", CPF); });
    }


    private void validateRG(String RG) {
        patientRepository.findByRg(RG).ifPresent(patient -> {
            throw new PatientAlreadyExistsException("RG", RG); });
    }

    private void validateEmail(String email) {
        patientRepository.findByEmail(email).ifPresent(professional -> {
            throw new PatientAlreadyExistsException("e-mail", email); });
    }

    private void validateCpfUpdate(String oldCPF, String newCPF) {
        if(!Objects.equals(oldCPF, newCPF)) {
            validateCPF(newCPF);
        }
    }

    private void validateRgUpdate(String oldRG, String newRG) {
        if(!Objects.equals(oldRG, newRG)) {
            validateRG(newRG);
        }
    }

    private void validateEmailUpdate(String oldEmail, String newEmail) {
        if(!Objects.equals(oldEmail, newEmail)) {
            validateEmail(newEmail);
        }
    }

}
