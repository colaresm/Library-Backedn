package com.library.libraryWDA.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.libraryWDA.dto.examPrediction.ExamPredictionCreateRequest;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionDetailsResponse;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionListItemResponse;
import com.library.libraryWDA.exception.examPrediction.ExamPredictionNotFoundException;
import com.library.libraryWDA.model.ExamPrediction;
import com.library.libraryWDA.mapper.ExamPredictionMapper;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.repository.ExamPredictionRepository;
import com.library.libraryWDA.service.ExamPredictionService;
import com.library.libraryWDA.service.PatientService;
import com.library.libraryWDA.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ExamPredictionServiceImpl implements ExamPredictionService {
    private final ExamPredictionMapper examPredictionMapper;
    
    private final ExamPredictionRepository examPredictionRepository;

    private final PatientService patientService;

    private final ClientService professionalService;

    @Autowired
    public ExamPredictionServiceImpl(ExamPredictionMapper examPredictionMapper, ExamPredictionRepository examPredictionRepository,PatientService patientService,ClientService professionalService) {
        this.examPredictionMapper = examPredictionMapper;
        this.examPredictionRepository = examPredictionRepository;
        this.patientService = patientService;
        this.professionalService = professionalService;
    }
    public void create(ExamPredictionCreateRequest examPredictionCreateRequest) {
        ExamPrediction examPredictionToCreate = examPredictionMapper.toModel(examPredictionCreateRequest);
      
        Patient patientFound = patientService.getById(examPredictionCreateRequest.getPatientId());
        
        Client professionalFound = professionalService.getById(examPredictionCreateRequest.getProfessionalId());

        examPredictionToCreate.setProfessional(professionalFound);

        examPredictionToCreate.setPatient(patientFound);

        examPredictionRepository.save(examPredictionToCreate);
    }
    @Override
    @Transactional
    public Page<ExamPredictionListItemResponse> findAllByPatiendId(UUID patientId, Pageable pageable) {
        Patient patientFound= patientService.getById(patientId);
        return examPredictionRepository.findAllByPatient(patientFound, pageable).map(examPredictionMapper::toExamPredictionListItemResponse);
    }
    public ExamPredictionDetailsResponse getExamPredictionById(UUID id) {
        ExamPrediction foundExamPrediction= getById(id);
        return examPredictionMapper.toExamPredictionDetailsResponse(foundExamPrediction);
    }
    public ExamPrediction getById(UUID id){
        return examPredictionRepository.findById(id).orElseThrow(() -> new ExamPredictionNotFoundException(id));
    }  
}
