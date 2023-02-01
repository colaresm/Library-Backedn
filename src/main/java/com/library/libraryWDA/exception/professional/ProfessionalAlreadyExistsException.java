package com.library.libraryWDA.exception.professional;

import javax.persistence.EntityExistsException;

public class ProfessionalAlreadyExistsException extends EntityExistsException {

    public ProfessionalAlreadyExistsException(String field, String value) {
        super(String.format("Cliente com %s %s já existe.", field, value));
    }
}