package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.examPrediction.ExamPredictionCreateRequest;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionDetailsResponse;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionListItemResponse;
import com.library.libraryWDA.model.Patient;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamPredictionService {
    void create(ExamPredictionCreateRequest examPredictionCreateRequest);


    Page<ExamPredictionListItemResponse> findAllByPatiendId(UUID patientId, Pageable pageable);
    
 
    ExamPredictionDetailsResponse getExamPredictionById(UUID id);
}
