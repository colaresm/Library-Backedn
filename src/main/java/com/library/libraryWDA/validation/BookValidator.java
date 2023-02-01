package com.library.libraryWDA.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.libraryWDA.dto.book.BookCreateRequest;
import com.library.libraryWDA.exception.book.BookAlreadyExistsException;
import com.library.libraryWDA.exception.book.BookPersistenceException;
import com.library.libraryWDA.repository.BookRepository;

@SuppressWarnings("unused")

@Component
public class BookValidator {
    
    @Autowired
    private BookRepository bookRepository;

    public void validateForCreation(BookCreateRequest BookCreateRequest) {
        validateTitle(BookCreateRequest.getTitle());
        validadeQuantity(BookCreateRequest.getQuantity());

    }
  
    private void validateTitle(String title) {
        bookRepository.findByTitle(title).ifPresent(book -> {
            throw new BookAlreadyExistsException("título", title); });
    }

    private void validadeQuantity(Integer quantity){
        if(quantity<=0)  throw new BookPersistenceException();
    }


}
