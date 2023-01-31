package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.ExamsControllerDocs;
import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.exam.ExamCreateRequest;
import com.library.libraryWDA.dto.exam.ExamUpdateRequest;
import com.library.libraryWDA.model.Exam;
import com.library.libraryWDA.dto.exam.ExamListItemResponse;
import com.library.libraryWDA.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/exams")
public class ExamsController implements ExamsControllerDocs {
    private final ExamService examService;

    @Autowired
    public ExamsController(ExamService examService) {
        this.examService = examService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @GetMapping("/page")
    public ResponseEntity<PageResult<ExamListItemResponse>> getPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResult<ExamListItemResponse> pageResult = new PageResult(examService.findAll(pageable));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ExamCreateRequest examCreateRequest) {
        examService.create(examCreateRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @GetMapping(value = "/all")
    public List<Exam> getAll() {
        return examService.getAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PutMapping
    public void update(@RequestBody @Valid ExamUpdateRequest examUpdateRequest) {
        examService.update(examUpdateRequest);
    }

    @GetMapping(value = "/{id}")
    public Exam getById(@PathVariable UUID id) {
        return examService.getById(id);
    }
}
