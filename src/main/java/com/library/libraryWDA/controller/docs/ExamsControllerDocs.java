package com.library.libraryWDA.controller.docs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.exam.ExamCreateRequest;
import com.library.libraryWDA.dto.exam.ExamUpdateRequest;
import com.library.libraryWDA.model.Exam;
import com.library.libraryWDA.dto.exam.ExamListItemResponse;
import org.springframework.web.bind.annotation.*;

@Api("Exams management")

public interface ExamsControllerDocs {

        @ApiOperation("Exams creation operation")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Success exam creation"),
                        @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or exam already registered on system")
        })
        void create(ExamCreateRequest examCreateRequest);

        @ApiOperation(value = "Change exam operation")
        void update(ExamUpdateRequest examUpdateRequest);

        @ApiOperation("List all registered exams")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Return all registered exams"),
        })
        ResponseEntity<PageResult<ExamListItemResponse>> getPage(int page, int size);

        @ApiOperation("GET Exam by Id")
        Exam getById(@PathVariable UUID id);

        @ApiOperation("GET Exam list")
        List<Exam> getAll();

}
