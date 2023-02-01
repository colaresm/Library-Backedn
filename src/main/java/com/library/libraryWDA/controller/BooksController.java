package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.BooksControllerDocs;
import com.library.libraryWDA.dto.book.BookCreateRequest;
import com.library.libraryWDA.dto.book.BookListItemResponse;
import com.library.libraryWDA.model.Book;
import com.library.libraryWDA.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/books")
public class BooksController implements BooksControllerDocs {
    @Autowired
    private BookService bookService;

  //  @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_PEOPLE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid BookCreateRequest bookCreateRequest) {
        bookService.create(bookCreateRequest);
    }

    
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<BookListItemResponse>> getAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }
    
     // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
     @GetMapping(value = "/{Id}")
     public Book getById(@PathVariable UUID Id) {
         return bookService.getById(Id);
     }


    
}
