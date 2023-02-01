package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.book.BookCreateRequest;

public interface BookService {

    void create(BookCreateRequest bookCreateRequest);
    
}
