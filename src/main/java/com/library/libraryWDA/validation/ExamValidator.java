package com.library.libraryWDA.validation;


import java.util.UUID;

import com.library.libraryWDA.dto.exam.ExamCreateRequest;
import com.library.libraryWDA.dto.exam.ExamUpdateRequest;
import com.library.libraryWDA.exception.exam.ExamExistsException;
import com.library.libraryWDA.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")

@Component
public class ExamValidator {
    @Autowired
    private ExamRepository examRepository;
    public void validateForCreation(ExamCreateRequest examCreateRequest){
        validateCode(examCreateRequest.getCode());
        validateName(examCreateRequest.getName());
    }
    public void validadeForUpdate( ExamUpdateRequest examUpdateRequest){
        validateNameUpdate(examUpdateRequest.getId(),examUpdateRequest.getName());
        validateCodeUpdate(examUpdateRequest.getId(),examUpdateRequest.getCode());
      }
  
    private void validateName( String Name) {
        examRepository.findByName(Name).ifPresent(exam -> {
            throw new ExamExistsException(); });
    }
    private void validateCode( String Code) {
        examRepository.findByCode(Code).ifPresent(exam -> {
            throw new ExamExistsException(); });
    }
    
    private void validateCodeUpdate( UUID examId, String Code) {
         
        examRepository.findByCode(examId,Code).ifPresent(exam -> {
            throw new ExamExistsException(); });
         
    }
    private void validateNameUpdate( UUID examId, String Name) {
         
        examRepository.findByName(examId,Name).ifPresent(exam -> {
            throw new ExamExistsException(); });
         
    }
}
