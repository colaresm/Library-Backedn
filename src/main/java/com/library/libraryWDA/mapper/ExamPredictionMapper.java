package com.library.libraryWDA.mapper;

import java.io.IOException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
 

import com.library.libraryWDA.dto.examPrediction.ExamPredictionCreateRequest;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionDetailsResponse;
import com.library.libraryWDA.dto.examPrediction.ExamPredictionListItemResponse;
import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.model.ExamPrediction;

@Mapper(componentModel = "spring")
public interface ExamPredictionMapper {
    @Mapping(target = "segmentedExam", source = "segmentedExam", qualifiedByName = "multipartFileToDocument")
    ExamPrediction toModel(ExamPredictionCreateRequest examPredictionCreateRequest); 
    
    @Mapping(target = "professional", source = "professional")
    ExamPredictionListItemResponse toExamPredictionListItemResponse(ExamPrediction examPrediction);

    
    ExamPredictionDetailsResponse toExamPredictionDetailsResponse(ExamPrediction examPrediction);
    
    @Named("multipartFileToDocument")
    static Document multipartFileToDocument(MultipartFile multipartFile) throws IOException {
        if(multipartFile == null) {
            return null;
        }

        Document segmentedExam = new Document();

        segmentedExam.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        segmentedExam.setContent(multipartFile.getBytes());
        segmentedExam.setSize(multipartFile.getSize());
        segmentedExam.setType(multipartFile.getContentType());

        return segmentedExam;
    }
}
