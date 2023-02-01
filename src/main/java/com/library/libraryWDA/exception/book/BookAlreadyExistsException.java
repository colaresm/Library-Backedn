package com.library.libraryWDA.exception.book;

import javax.persistence.EntityExistsException;
 

public class BookAlreadyExistsException   extends EntityExistsException{
    public BookAlreadyExistsException(String field, String value) {
        super(String.format("Livro com %s %s já existe.", field, value));
    }
}
