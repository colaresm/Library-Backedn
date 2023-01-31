package com.library.libraryWDA.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.libraryWDA.dto.examPrediction.ExamPredictionListItemResponse;
import com.library.libraryWDA.model.ExamPrediction;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.model.enums.Prediction;

@Repository
public interface ExamPredictionRepository extends JpaRepository<ExamPrediction, UUID> {
   Page<ExamPrediction> findAllByPatient(Patient patientId,Pageable pageable);
}
