package com.library.libraryWDA.exception.exam;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

public class ExamNotFoundException  extends EntityNotFoundException{
    public ExamNotFoundException(UUID id) {
        super(String.format("O Exame com id %s não existe", id));
}
}