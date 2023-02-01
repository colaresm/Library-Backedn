package com.library.libraryWDA.controller.docs;

import com.library.libraryWDA.dto.rent.RentCreateRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api("Rents module management")
public interface RentControllerDocs {
    @ApiOperation("Rent creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success Rent creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or rent already registered on system")
    })
    void create(RentCreateRequest rentCreateRequest);
}
