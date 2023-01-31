package com.library.libraryWDA.exception.exam;

import javax.persistence.EntityExistsException;

public class ExamExistsException extends EntityExistsException {

    public ExamExistsException() {
        super(String.format("Exame já cadastrado."));
    }
}