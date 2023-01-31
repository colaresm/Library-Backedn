package com.library.libraryWDA.controller.docs;

import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.client.ClientCreateRequest;
import com.library.libraryWDA.dto.client.ClientDetailsResponse;
import com.library.libraryWDA.dto.client.ClientListItemResponse;
import com.library.libraryWDA.dto.client.ClientUpdateRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Api("Clients module management")
public interface ProfessionalsControllerDocs {

    @ApiOperation("Professional creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success professional creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or professional already registered on system")
    })
    void create(ClientCreateRequest professionalCreateRequest, MultipartFile profilePicture);

    @ApiOperation("List all registered Clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the paginated Clients list"),
    })
    public ResponseEntity<PageResult<ClientListItemResponse>> getPage(String status, int page, int size);

    @ApiOperation("List all registered doctors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the paginated doctors list"),
    })
    public ResponseEntity<PageResult<ClientListItemResponse>> getAllDoctors(String status, int page, int size);

    @CrossOrigin(origins = {"*"})
    @ApiOperation("Professional deactivation by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success professional deactivation"),
            @ApiResponse(code = 404, message = "Professional not found")
    })
    void setDeactivated(UUID id);
    @CrossOrigin(origins = {"*"})
    @ApiOperation("Professional activation by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success professional activation"),
            @ApiResponse(code = 404, message = "Professional not found")
    })
    void setActivated(UUID id);

    @ApiOperation("Professional update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success professional update"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or professional already registered on system"),
            @ApiResponse(code = 404, message = "Professional not found")
    })
    void update(ClientUpdateRequest professionalUpdateRequest, MultipartFile profilePicture);
    @ApiOperation("GET Professional by Id")
    ResponseEntity<ClientDetailsResponse> getProfessionalById(@PathVariable("id")  UUID id);

    @ApiOperation("List all activated Clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the non-paged list of activated Clients"),
    })
    ResponseEntity<List<ClientListItemResponse>> getAllActive();
}
