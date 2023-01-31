package com.library.libraryWDA.service.impl;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.libraryWDA.dto.exam.ExamCreateRequest;
import com.library.libraryWDA.dto.exam.ExamListItemResponse;
import com.library.libraryWDA.dto.exam.ExamUpdateRequest;
import com.library.libraryWDA.exception.exam.ExamNotFoundException;
import com.library.libraryWDA.mapper.ExamMapper;
import com.library.libraryWDA.model.Exam;
import com.library.libraryWDA.repository.ExamRepository;
import com.library.libraryWDA.service.ExamService;
import com.library.libraryWDA.validation.ExamValidator;

@Service
public class ExamServiceImpl implements ExamService  {
      
    private final ExamMapper examMapper;
    private final ExamRepository examRepository;
    private final ExamValidator examValidator;

    @Autowired
    public ExamServiceImpl(ExamMapper examMapper, ExamRepository examRepository, ExamValidator examValidator) {
        this.examMapper = examMapper;
        this.examRepository = examRepository;
        this.examValidator = examValidator;
     
    }
    public void create(ExamCreateRequest examCreateRequest) {
        examValidator.validateForCreation(examCreateRequest);
        Exam examToCreate = examMapper.toModel(examCreateRequest);
        examRepository.save(examToCreate);
    }
    
    public Page<ExamListItemResponse> findAll(Pageable pageable) {
        return examRepository.findAll(pageable).map(examMapper::toExamListItemResponse);
    }

    public void update(ExamUpdateRequest examUpdateRequest) {
        Exam foundExam = getById(examUpdateRequest.getId());

        examValidator.validadeForUpdate(examUpdateRequest);

        foundExam.setCode(examUpdateRequest.getCode());
        foundExam.setName(examUpdateRequest.getName());
        examRepository.save(foundExam);
    }

    public Exam getById(UUID id){
        return examRepository.findById(id).orElseThrow(() -> new ExamNotFoundException(id));
    }  
    public List<Exam> getAll(){
        return examRepository.findAll();
    }
}
