package com.library.libraryWDA.controller.docs;

import com.library.libraryWDA.dto.book.BookCreateRequest;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
 

@Api("Books module management")
public interface BooksControllerDocs {
    @ApiOperation("Book creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success Book creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or Book already registered on system")
    })
    void create(BookCreateRequest bookCreateRequest);
    
}
