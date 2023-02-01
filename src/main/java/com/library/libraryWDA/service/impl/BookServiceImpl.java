package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.exception.book.BookNotFoundException;
import com.library.libraryWDA.validation.BookValidator;
import com.library.libraryWDA.dto.book.BookCreateRequest;
import com.library.libraryWDA.dto.book.BookListItemResponse;
import com.library.libraryWDA.mapper.BookMapper;
import com.library.libraryWDA.model.Book;
import com.library.libraryWDA.repository.BookRepository;
import com.library.libraryWDA.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService{
    
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final BookValidator bookValidator;

   @Autowired
   public BookServiceImpl( BookRepository bookRepository,BookMapper bookMapper,BookValidator bookValidator) {
       this.bookMapper = bookMapper;
       this.bookRepository = bookRepository;
       this.bookValidator=bookValidator;
    
   }

   @Override
   public void create(BookCreateRequest bookCreateRequest) {
       bookValidator.validateForCreation(bookCreateRequest);

       Book bookToCreate = bookMapper.toModel(bookCreateRequest);
       bookRepository.save(bookToCreate);
   }
   @Override
   public Book getById(UUID id) {
       return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
   }

   @Override
   public  List<BookListItemResponse> findAll(){

        return bookRepository.findAll()
        .stream()
        .map(bookMapper::toBookListItemResponse)
        .collect(Collectors.toList());
   }
 
  
}
