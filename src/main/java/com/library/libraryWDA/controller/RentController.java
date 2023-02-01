package com.library.libraryWDA.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.library.libraryWDA.controller.docs.RentControllerDocs;
import com.library.libraryWDA.dto.rent.RentCreateRequest;
import com.library.libraryWDA.service.RentService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping(value = "/rents")
public class RentController implements  RentControllerDocs{

    @Autowired
    private RentService rentService;

    
    //  @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_PEOPLE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid RentCreateRequest rentCreateRequest) {
        rentService.create(rentCreateRequest);
    }


    
}
