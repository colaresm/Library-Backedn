package com.library.libraryWDA.controller.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.libraryWDA.dto.book.BookCreateRequest;
import com.library.libraryWDA.dto.book.BookListItemResponse;
import com.library.libraryWDA.model.Book;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.UUID;

@Api("Books module management")
public interface BooksControllerDocs {
    @ApiOperation("Book creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success Book creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or Book already registered on system")
    })
    void create(BookCreateRequest bookCreateRequest);

    @ApiOperation("List all books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the non-paged list of books"),
    })
    ResponseEntity<List<BookListItemResponse>> getAll();

    @ApiOperation("GET book by Id")
     Book getById(@PathVariable UUID id);
    
}
