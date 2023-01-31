package com.library.libraryWDA.controller;


import com.library.libraryWDA.controller.docs.ProfessionalAvailabilityDocs;
import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityCreateRequest;
import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityListResponse;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.service.AuthenticationService;
import com.library.libraryWDA.service.ProfessionalAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/Clients-availability")
public class ProfessionalAvailabilityController implements ProfessionalAvailabilityDocs {
    private final ProfessionalAvailabilityService professionalAvailabilityService;

    private final AuthenticationService authenticationService;

    @Autowired
    public ProfessionalAvailabilityController(ProfessionalAvailabilityService professionalAvailabilityService,
                                              AuthenticationService authenticationService) {
        this.professionalAvailabilityService = professionalAvailabilityService;
        this.authenticationService = authenticationService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANTE', 'ROLE_DOCTOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ProfessionalAvailabilityCreateRequest professionalAvailabilityCreateRequest) {
        professionalAvailabilityService.create(professionalAvailabilityCreateRequest);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANTE', 'ROLE_DOCTOR')")
    @GetMapping(value = "/{duration}")
    public ArrayList<String> getListProfessionalAvailability(@PathVariable Integer duration) {
        return professionalAvailabilityService.findListProfessionalAvailability(duration);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANTE')")
    @GetMapping(value = "/list-by-profissional/{professionalId}")
    public List<ProfessionalAvailabilityListResponse> getAllProfessionalAvailability(@PathVariable Client professionalId) {
        return professionalAvailabilityService.findAllProfessionalAvailability(professionalId);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
    @GetMapping(value = "/list-by-doctor-logged")
    public List<ProfessionalAvailabilityListResponse> getAllProfessionalAvailabilityByDoctorLogged() {
        return professionalAvailabilityService.findAllProfessionalAvailability(authenticationService.getProfessionalLogged());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANTE', 'ROLE_DOCTOR')")
    @DeleteMapping("/{availabilityProfessionalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessionalAvailabilityById(@PathVariable UUID availabilityProfessionalId) {
        professionalAvailabilityService.deleteProfessionalAvailabilitylById(availabilityProfessionalId);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE ATTENDANT')")
    @DeleteMapping("professional/{professionalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessionalAvailabilityByProfessionalId(@PathVariable Client professionalId) {
        professionalAvailabilityService.deleteProfessionalAvailabilityByProfessionalId(professionalId);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
    @DeleteMapping("/doctor-logged")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessionalAvailabilityByDoctorLogged() {
        professionalAvailabilityService.deleteProfessionalAvailabilityByProfessionalId(authenticationService.getProfessionalLogged());
    }
}
