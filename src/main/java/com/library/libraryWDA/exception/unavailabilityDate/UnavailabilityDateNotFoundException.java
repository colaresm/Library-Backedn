package com.library.libraryWDA.exception.unavailabilityDate;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UnavailabilityDateNotFoundException extends EntityNotFoundException {
    public UnavailabilityDateNotFoundException (UUID id){ super(String.format("Data indisponível com esse id %s não existe", id));}
}
