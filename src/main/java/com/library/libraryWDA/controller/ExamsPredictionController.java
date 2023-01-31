package com.library.libraryWDA.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.libraryWDA.controller.docs.ExamsPredictionControllerDocs;
import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionCreateRequest;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionDetailsResponse;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionListItemResponse;
import com.library.libraryWDA.model.ExamPrediction;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.service.ExamPredictionService;
@RestController
@RequestMapping(value = "/exams-prediction")
public class ExamsPredictionController implements ExamsPredictionControllerDocs{
    private final ExamPredictionService examPredictionService;

    @Autowired
    public ExamsPredictionController(ExamPredictionService examPredictionService) {
        this.examPredictionService = examPredictionService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @ModelAttribute ExamPredictionCreateRequest examPredictionCreateRequest,
                       @RequestPart(required = false) MultipartFile segmentedExam) {
                        examPredictionCreateRequest.setSegmentedExam(segmentedExam);
                        examPredictionService.create(examPredictionCreateRequest);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @GetMapping(value = "{patientId}/page")
    public ResponseEntity<PageResult<ExamPredictionListItemResponse>> getPage(@RequestParam(defaultValue = "0") int page, 
                                                                       @RequestParam(defaultValue = "5") int size,@PathVariable UUID patientId) {
        Pageable pageable = PageRequest.of(page, size);
        PageResult<ExamPredictionListItemResponse> pageResult = new PageResult(examPredictionService.findAllByPatiendId( patientId, pageable));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ExamPredictionDetailsResponse> getExamPredictionById(@PathVariable("id")  UUID id) {
        return new ResponseEntity<>( examPredictionService.getExamPredictionById(id), HttpStatus.OK);
    }
}
