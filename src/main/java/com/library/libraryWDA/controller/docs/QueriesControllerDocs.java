package com.library.libraryWDA.controller.docs;

import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.query.QueryCreateRequest;
import com.library.libraryWDA.dto.query.QueryListItemResponse;
import com.library.libraryWDA.dto.query.QueryUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Api("Queries management")
public interface QueriesControllerDocs {

    @ApiOperation("Query creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success query creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or query already registered on system")
    })
    void create(QueryCreateRequest queryCreateRequest, MultipartFile[] attachments,
                HttpServletRequest request, HttpServletResponse response) throws IOException;

    @ApiOperation("List all registered queries by patient id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return all registered queries by patient id"),
    })
    ResponseEntity<PageResult<QueryListItemResponse>> getPageByPatientId(UUID patientId, int page, int size);


    @ApiOperation("Query update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success query update"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or query already registered on system"),
            @ApiResponse(code = 404, message = "Query not found")
 
        })
    void update(QueryUpdateRequest queryUpdateRequest, MultipartFile[] attachments) throws IOException;
 
}
