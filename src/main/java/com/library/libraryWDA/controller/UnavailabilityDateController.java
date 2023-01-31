package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.UnavailabilityDateControllerDocs;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateCreateRequest;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateListItemResponse;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateUpdateRequest;
import com.library.libraryWDA.model.UnavailabilityDate;
import com.library.libraryWDA.service.UnavailabilityDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/unavailability-dates")
public class UnavailabilityDateController implements UnavailabilityDateControllerDocs {

    @Autowired
    private UnavailabilityDateService unavailabilityDateService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid UnavailabilityDateCreateRequest unavailabilityDateCreateRequest){
        unavailabilityDateService.create(unavailabilityDateCreateRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UnavailabilityDateListItemResponse>> findAll(){
        return new ResponseEntity<>(unavailabilityDateService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PutMapping
    public void update(@RequestBody @Valid UnavailabilityDateUpdateRequest unavailabilityDateUpdateRequest){
        unavailabilityDateService.update(unavailabilityDateUpdateRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        unavailabilityDateService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @GetMapping(value = "/{id}")
    public UnavailabilityDate getById(@PathVariable UUID id) {
        return unavailabilityDateService.getById(id);
    }
}

