package com.library.libraryWDA.exception.availabilityProfessional;

import javax.persistence.EntityExistsException;
public class AvailabilityProfessionalExistsException extends EntityExistsException {

    public AvailabilityProfessionalExistsException() {
        super(String.format("Disponibilidade já cadastrada."));
    }
}
