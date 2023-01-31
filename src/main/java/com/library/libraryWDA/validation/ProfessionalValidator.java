package com.library.libraryWDA.validation;

import com.library.libraryWDA.dto.client.ClientCreateRequest;
import com.library.libraryWDA.dto.client.ClientUpdateRequest;
import com.library.libraryWDA.exception.professional.ProfessionalAlreadyExistsException;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@SuppressWarnings("unused")

@Component
public class ProfessionalValidator {

    @Autowired
    private ClientRepository professionalRepository;

    public void validateForCreation(ClientCreateRequest professionalCreateRequest) {
        validateCPF(professionalCreateRequest.getCpf());
        validateRG(professionalCreateRequest.getRg());
        validateEmail(professionalCreateRequest.getEmail());
        if(professionalCreateRequest.getPosition()==Position.valueOf("DOCTOR")){ validateCRM(professionalCreateRequest.getCrm());}
       
    }

    public void validateForUpdate(Client foundProfessional, ClientUpdateRequest professionalUpdateRequest) {
        validateCpfUpdate(foundProfessional.getCpf(), professionalUpdateRequest.getCpf());
        if(professionalUpdateRequest.getPosition()==Position.valueOf("DOCTOR")){ validateCrmUpdate(foundProfessional.getCrm(), professionalUpdateRequest.getCrm());}
        validateRgUpdate(foundProfessional.getRg(), professionalUpdateRequest.getRg());
        validateEmailUpdate(foundProfessional.getEmail(), professionalUpdateRequest.getEmail());
    }

    private void validateCPF(String CPF) {
        professionalRepository.findByCpf(CPF).ifPresent(professional -> {
            throw new ProfessionalAlreadyExistsException("CPF", CPF); });
    }

    private void validateCRM(String CRM) {
        professionalRepository.findByCrm(CRM).ifPresent(professional -> {
            throw new ProfessionalAlreadyExistsException("CRM", CRM); });
    }

    private void validateRG(String RG) {
        professionalRepository.findByRg(RG).ifPresent(professional -> {
            throw new ProfessionalAlreadyExistsException("RG", RG); });
    }

    private void validateEmail(String email) {
        professionalRepository.findByEmail(email).ifPresent(professional -> {
            throw new ProfessionalAlreadyExistsException("e-mail", email); });
    }

    private void validateCpfUpdate(String oldCPF, String newCPF) {
        if(!Objects.equals(oldCPF, newCPF)) {
            validateCPF(newCPF);
        }
    }

    private void validateCrmUpdate(String oldCRM, String newCRM) {
        if(!Objects.equals(oldCRM, newCRM)) {
            validateCRM(newCRM);
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
