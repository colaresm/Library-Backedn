package com.library.libraryWDA.exception.availabilityProfessional;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
public class AvailabilityProfessionalNotFoundException extends EntityNotFoundException{
    public AvailabilityProfessionalNotFoundException(UUID id) {
        super(String.format("A disponibilidade com id %s não existe", id));
}
}
