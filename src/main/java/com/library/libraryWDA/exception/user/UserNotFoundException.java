package com.library.libraryWDA.exception.user;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(UUID id) {
        super(String.format("Usuário com id %s não existe.", id));
    }
    public UserNotFoundException(String email){
        super(String.format("Usuario com email %s não existe", email));
    }
}
