package com.library.libraryWDA.exception.patient;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class PatientNotFoundException extends EntityNotFoundException {

    public PatientNotFoundException(UUID id){
        super(String.format("Paciente com o id %s não existe!", id));
    }
    public PatientNotFoundException(String email){
        super(String.format("Paciente com o email %s não existe!", email));
    }
}
