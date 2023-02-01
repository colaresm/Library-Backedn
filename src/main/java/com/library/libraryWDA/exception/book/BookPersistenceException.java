package com.library.libraryWDA.exception.book;

import javax.persistence.PersistenceException;

public class BookPersistenceException  extends PersistenceException{
    public BookPersistenceException() {
        super(String.format("A quantidade de livros deve ser superior a zero"));
    }
}
