package com.library.libraryWDA.dto.examPrediction;

import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.springframework.web.multipart.MultipartFile;

import com.library.libraryWDA.model.enums.Prediction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamPredictionCreateRequest {
    
    private MultipartFile segmentedExam;

    @Enumerated(EnumType.STRING)
    private Prediction prediction;

    private UUID patientId;

    private UUID professionalId;

    private Integer wavesPQuantity;

    private Integer wavesTQuantity;

    private Integer wavesQRSQuantity;

    private Integer wavesVEBQuantity;

    private Integer wavesSVEBQuantity;


}
