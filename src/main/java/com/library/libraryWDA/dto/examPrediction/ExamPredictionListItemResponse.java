package com.library.libraryWDA.dto.examPrediction;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class ExamPredictionListItemResponse {
     private UUID id;

     private LocalDateTime createdAt;
     
     private ExamPredictionProfessionalRequest professional;
}
