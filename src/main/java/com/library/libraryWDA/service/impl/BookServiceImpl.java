package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.book.BookCreateRequest;
import com.library.libraryWDA.mapper.BookMapper;
import com.library.libraryWDA.model.Book;
import com.library.libraryWDA.repository.BookRepository;
import com.library.libraryWDA.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService{
    
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

   // private final ProfessionalValidator professionalValidator;
   @Autowired
   public BookServiceImpl( BookRepository bookRepository,BookMapper bookMapper) {
       this.bookMapper = bookMapper;
       this.bookRepository = bookRepository;
    
   }

   @Override
   public void create(BookCreateRequest bookCreateRequest) {
    //   professionalValidator.validateForCreation(professionalCreateRequest);

       Book bookToCreate = bookMapper.toModel(bookCreateRequest);
       bookRepository.save(bookToCreate);
   }
    
}
