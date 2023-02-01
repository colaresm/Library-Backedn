package com.library.libraryWDA.exception.book;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;
public class BookNotFoundException extends EntityNotFoundException {
    
    public BookNotFoundException(UUID id) {
        super(String.format("Livro com id %s não existe.", id));
    }
}
