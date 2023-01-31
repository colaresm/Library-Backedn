package com.library.libraryWDA.controller.docs;

import com.library.libraryWDA.dto.patient.PatientResponse;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateListItemResponse;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateCreateRequest;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateUpdateRequest;
import com.library.libraryWDA.model.Exam;
import com.library.libraryWDA.model.UnavailabilityDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Api("Unavailability date module management")
public interface UnavailabilityDateControllerDocs {

    @ApiOperation("Unavailability date creation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success unavailability date creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value")
    })
    void create(UnavailabilityDateCreateRequest unavailabilityDateCreateRequest);

    @ApiOperation("List all unavailability dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registered unavailability dates")
    })
    ResponseEntity<List<UnavailabilityDateListItemResponse>> findAll();

    @ApiOperation("Unavailability date update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success unavailability date update"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value"),
            @ApiResponse(code = 404, message = "Unavailability date not found")
    })
    void update(UnavailabilityDateUpdateRequest unavailabilityDateUpdateRequest);


    @ApiOperation(value = "Delete a unavailability date")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Delete successfully"),
            @ApiResponse(code = 404, message = "Unavailability date not found")
    })
    void deleteById(UUID id);

    @ApiOperation("Return registered unavailability date by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Unavailability date returned successfully"),
            @ApiResponse(code = 404, message = "Unavailability date not found")
    })
    UnavailabilityDate getById(@PathVariable UUID id);

}