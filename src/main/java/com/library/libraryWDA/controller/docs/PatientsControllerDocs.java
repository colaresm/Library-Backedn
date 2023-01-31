package com.library.libraryWDA.controller.docs;


import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.patient.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Api("Patients module management")
public interface PatientsControllerDocs {

    @ApiOperation("Patient creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success patient creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or patient already registered on system")
    })
    void create(PatientCreateRequest patientCreateRequest, MultipartFile profilePicture);

    @ApiOperation("Patient Simplified creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success patient creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or patient already registered on system")
    })
    void createSimplified(PatientSimplifiedCreateRequest patientSimplifiedCreateRequest);


    @ApiOperation(value = "Returns the paginated patients list")
    public ResponseEntity<PageResult<PatientListItemResponse>> getPage(String status, int page, int size);

    @ApiOperation("Patient deactivation by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success patient deactivation"),
            @ApiResponse(code = 404, message = "Patient not found")
    })
    void setDeactivated(UUID id);

    @ApiOperation("Patient activation by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success patient activation"),
            @ApiResponse(code = 404, message = "Patient not found")
    })
    void setActivated(UUID id);

    @ApiOperation("Patient update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success patient update"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or professional already registered on system"),
            @ApiResponse(code = 404, message = "Patient not found")
    })
    void update(PatientUpdateRequest patientUpdateRequest,  MultipartFile profilePicture);

    @ApiOperation("Return registered patient by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "patient returned successfully"),
            @ApiResponse(code = 404, message = "Patient not found")
    })
    ResponseEntity<PatientResponse> getById(UUID id);

    @ApiOperation(value = "Returns data patients to reminders page")
    ResponseEntity<PageResult<PatientReminderListItemResponse>> getPage (int page, int size);

    @ApiOperation("List all activated patients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the non-paged list of activated patients"),
    })
    ResponseEntity<List<PatientListItemResponse>> getAllActive();
}