package com.library.libraryWDA.mapper;

import org.mapstruct.Mapper;

import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientCreateRequest;
import com.library.libraryWDA.model.ClinicalHistoryPatient;

@Mapper(componentModel = "spring")
public interface ClinicalHistoryPatientMapper {
    ClinicalHistoryPatient toModel( ClinicalHistoryPatientCreateRequest clinicalHistoryPatientCreateRequest);
}
