package com.library.libraryWDA.model;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.*;

import com.library.libraryWDA.model.BaseEntity;
import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.enums.Prediction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "examsPrediction")
public class ExamPrediction extends BaseEntity{
   

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "segmented_exam_id")
    private Document segmentedExam;

    @Enumerated(EnumType.STRING)
    private Prediction prediction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id" )
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id")
    private Client professional;

    private Integer wavesPQuantity;

    private Integer wavesTQuantity;

    private Integer wavesQRSQuantity;

    private Integer wavesVEBQuantity;

    private Integer wavesSVEBQuantity;

}
