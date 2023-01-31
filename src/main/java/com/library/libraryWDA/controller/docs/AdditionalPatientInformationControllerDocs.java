package com.library.libraryWDA.controller.docs;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationCreateRequest;
import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationListItemResponse;
import com.library.libraryWDA.model.Patient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Additional Informations management")
public interface AdditionalPatientInformationControllerDocs {
    @ApiOperation("Additional Information creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success additional information creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or additional information already registered on system")
    })
    void create(AdditionalPatientInformationCreateRequest additionalPatientInformationCreateRequest);

    @ApiOperation("returns  additional information  for patient")
    List<AdditionalPatientInformationListItemResponse>  findAllByPatientId(@PathVariable Patient patientId);
  
}
