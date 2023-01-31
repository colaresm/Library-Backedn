package com.library.libraryWDA.controller.docs;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientCreateRequest;
import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientListItemResponse;
import com.library.libraryWDA.model.Patient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Clinical history patient management")
public interface ClinicalHistoryPatientControllerDocs {
    @ApiOperation("Additional Information creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success clinical  creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or clinical history patient already registered on system")
    })
    void create(ClinicalHistoryPatientCreateRequest clinicalHistoryPatientCreateRequest);

    @ApiOperation("returns  clinical history for patient")
    List<ClinicalHistoryPatientListItemResponse>  findAllByPatientId(@PathVariable Patient patientId);
}
