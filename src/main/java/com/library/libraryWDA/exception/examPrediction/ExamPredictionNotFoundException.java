package com.library.libraryWDA.exception.examPrediction;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
public class ExamPredictionNotFoundException  extends EntityNotFoundException{
    public ExamPredictionNotFoundException(UUID id) {
        super(String.format("A predição com id %s não existe", id));
}
}
