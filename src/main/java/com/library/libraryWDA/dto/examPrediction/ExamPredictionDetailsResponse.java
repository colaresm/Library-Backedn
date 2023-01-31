package com.library.libraryWDA.dto.examPrediction;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.model.enums.Prediction;

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
public class ExamPredictionDetailsResponse {
    private Document segmentedExam;

    @Enumerated(EnumType.STRING)
    private Prediction prediction;

    private Integer wavesPQuantity;

    private Integer wavesTQuantity;

    private Integer wavesQRSQuantity;

    private Integer wavesVEBQuantity;

    private Integer wavesSVEBQuantity;


}

