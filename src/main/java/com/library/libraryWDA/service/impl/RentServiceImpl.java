package com.library.libraryWDA.service.impl;


import com.library.libraryWDA.service.RentService;
import com.library.libraryWDA.service.BookService;
import com.library.libraryWDA.service.ClientService;
import com.library.libraryWDA.dto.rent.RentCreateRequest;
import com.library.libraryWDA.mapper.RentMapper;
import com.library.libraryWDA.model.Book;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.Rent;
import com.library.libraryWDA.repository.RentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class RentServiceImpl  implements RentService{

    private final RentRepository rentRepository;

    private final RentMapper rentMapper;

    private final BookService bookService;

    private final ClientService clientService;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository,RentMapper rentMapper,ClientService clientService,BookService bookService) {

        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
        this.bookService = bookService;
        this.clientService=clientService;     
    }

    @Override
    public void create(RentCreateRequest rentCreateRequest) {
      ///  clientValidator.validateForCreation(professionalCreateRequest);

    
        Rent renToCreate = new Rent();
        
        Book book = bookService.getById(rentCreateRequest.getBookId());

        Client client = clientService.getById(rentCreateRequest.getClientId());

        renToCreate.setBook(book);

        renToCreate.setClient(client);

        rentRepository.save(renToCreate);
    }



    
}
