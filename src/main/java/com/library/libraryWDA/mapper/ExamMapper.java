package com.library.libraryWDA.mapper;

import com.library.libraryWDA.dto.exam.ExamCreateRequest;
import com.library.libraryWDA.dto.exam.ExamUpdateRequest;
import com.library.libraryWDA.dto.exam.ExamListItemResponse;
import com.library.libraryWDA.model.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    Exam toModel(ExamCreateRequest examCreateRequest);
    ExamCreateRequest toDTO(Exam exam);
    ExamListItemResponse toExamListItemResponse(Exam exam);
    Exam toModel(ExamUpdateRequest examUpdateRequest);    
}
