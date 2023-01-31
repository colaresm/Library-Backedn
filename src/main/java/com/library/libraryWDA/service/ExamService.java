package com.library.libraryWDA.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import com.library.libraryWDA.dto.exam.ExamCreateRequest;
import com.library.libraryWDA.dto.exam.ExamUpdateRequest;
import com.library.libraryWDA.model.Exam;
import com.library.libraryWDA.dto.exam.ExamListItemResponse;

public interface ExamService {
    Page<ExamListItemResponse> findAll(Pageable pageable);
    Exam getById(UUID id);
    void create(ExamCreateRequest examCreateRequest);
    void update(ExamUpdateRequest examUpdateRequest);
    List<Exam> getAll();
}
