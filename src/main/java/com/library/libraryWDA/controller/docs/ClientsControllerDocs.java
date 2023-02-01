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
public interface ClientsControllerDocs {
    @ApiOperation("Client creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success professional creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or client already registered on system")
    })
    void create(ClientCreateRequest professionalCreateRequest, MultipartFile profilePicture);

    @ApiOperation("List all registered Clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the paginated Clients list"),
    })
    public ResponseEntity<PageResult<ClientListItemResponse>> getPage(String status, int page, int size);



    @CrossOrigin(origins = {"*"})
    @ApiOperation("Client deactivation by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success client deactivation"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    void setDeactivated(UUID id);
    @CrossOrigin(origins = {"*"})
    @ApiOperation("Client activation by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success client activation"),
            @ApiResponse(code = 404, message = "Cli" +
                    "client not found")
    })
    void setActivated(UUID id);

    @ApiOperation("Client update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success Client update"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or Client already registered on system"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    void update(ClientUpdateRequest clientUpdateRequest, MultipartFile profilePicture);
    @ApiOperation("GET Client by Id")
    ResponseEntity<ClientDetailsResponse> getClientById(@PathVariable("id")  UUID id);

    @ApiOperation("List all activated Clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the non-paged list of activated Clients"),
    })
    ResponseEntity<List<ClientListItemResponse>> getAllActive();
}
