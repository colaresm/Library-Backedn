package com.library.libraryWDA.exception.professional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class ProfessionalNotFoundException extends EntityNotFoundException {

    public ProfessionalNotFoundException(UUID id) {
        super(String.format("Cliente com id %s não existe.", id));
    }
}