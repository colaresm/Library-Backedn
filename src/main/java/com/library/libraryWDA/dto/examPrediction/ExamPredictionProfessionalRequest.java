package com.library.libraryWDA.dto.examPrediction;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
 

public class ExamPredictionProfessionalRequest {
    private UUID id;
    private String name;
}
