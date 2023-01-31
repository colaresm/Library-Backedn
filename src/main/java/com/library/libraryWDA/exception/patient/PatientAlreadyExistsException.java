package com.library.libraryWDA.exception.patient;

import javax.persistence.EntityExistsException;

public class PatientAlreadyExistsException extends EntityExistsException {

    public PatientAlreadyExistsException(String field, String value) {
        super(String.format("Paciente com %s %s já existe.", field, value));
    }
}

