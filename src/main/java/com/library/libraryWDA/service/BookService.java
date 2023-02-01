package com.library.libraryWDA.service;

import java.util.List;
import java.util.UUID;

import com.library.libraryWDA.model.Book;
import com.library.libraryWDA.dto.book.BookCreateRequest;
import com.library.libraryWDA.dto.book.BookListItemResponse;

public interface BookService {

    void create(BookCreateRequest bookCreateRequest);

    List<BookListItemResponse> findAll();

    Book getById(UUID Id);
    
}
