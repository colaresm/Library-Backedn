package com.library.libraryWDA.controller.docs;


import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityCreateRequest;
import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityListResponse;
import com.library.libraryWDA.model.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
 

@Api("Clients availability  management")
public interface ProfessionalAvailabilityDocs {
    
    @ApiOperation("professional availability creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success  professional availability creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or  professional  availability already registered on system")
    })
    void create(ProfessionalAvailabilityCreateRequest availabilityProfessionalCreateRequest);
    @ApiOperation("returns available times")
    ArrayList<String> getListProfessionalAvailability(@PathVariable Integer duration);

    @ApiOperation("returns availability Clients  for professional")
    List<ProfessionalAvailabilityListResponse>  getAllProfessionalAvailability(@PathVariable Client professionalId);

    @ApiOperation("Returns availability Clients by doctor logged")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List all availability professional returned successfully"),
    })
    List<ProfessionalAvailabilityListResponse> getAllProfessionalAvailabilityByDoctorLogged();
  
    @ApiOperation("delete availability by id")
     void deleteProfessionalAvailabilityById(@PathVariable UUID availabilityProfessionalId) ;

     @ApiOperation("delete professional availability  by  professional id")
     void deleteProfessionalAvailabilityByProfessionalId(@PathVariable Client professionalId);

    @ApiOperation("Delete professional availability by doctor logged")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success professional availability deletion")
    })
    void deleteProfessionalAvailabilityByDoctorLogged();
}
