package com.library.libraryWDA.controller.docs;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionCreateRequest;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionDetailsResponse;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionListItemResponse;
import com.library.libraryWDA.model.Patient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api("Exam prediction module management")
public interface ExamsPredictionControllerDocs {
    
    @ApiOperation("Exam prediction creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success exam prediction creation"),
            @ApiResponse(code = 400, message = "Failed to perform exam prediction")
    })
    void create(ExamPredictionCreateRequest examPredictionCreateRequest, MultipartFile segmentedExam);

    @ApiOperation(value = "Returns the paginated exam predictions list")
    public ResponseEntity<PageResult<ExamPredictionListItemResponse>> getPage( int page, int size,@PathVariable UUID patientId);
   
    @ApiOperation("GET exam prediction by Id")
    ResponseEntity<ExamPredictionDetailsResponse> getExamPredictionById(@PathVariable("id")  UUID id);
}
