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
        validateEmail(professionalCreateRequest.getEmail());

    }

    public void validateForUpdate(Client foundProfessional, ClientUpdateRequest professionalUpdateRequest) {
        validateCpfUpdate(foundProfessional.getCpf(), professionalUpdateRequest.getCpf());
        validateEmailUpdate(foundProfessional.getEmail(), professionalUpdateRequest.getEmail());
    }

    private void validateCPF(String CPF) {
        professionalRepository.findByCpf(CPF).ifPresent(professional -> {
            throw new ProfessionalAlreadyExistsException("CPF", CPF); });
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

    private void validateEmailUpdate(String oldEmail, String newEmail) {
        if(!Objects.equals(oldEmail, newEmail)) {
            validateEmail(newEmail);
        }
    }
}
