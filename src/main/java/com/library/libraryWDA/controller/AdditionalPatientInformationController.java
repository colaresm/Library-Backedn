package com.library.libraryWDA.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryWDA.controller.docs.AdditionalPatientInformationControllerDocs;
import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationCreateRequest;
import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationListItemResponse;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.service.AdditionalPatientInformationService;
 

@RestController
@RequestMapping(value = "/additional-informations")
public class AdditionalPatientInformationController implements AdditionalPatientInformationControllerDocs {
    private final AdditionalPatientInformationService additionalPatientInformationService;
    @Autowired
    public AdditionalPatientInformationController(AdditionalPatientInformationService additionalPatientInformationService) {
        this.additionalPatientInformationService = additionalPatientInformationService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid AdditionalPatientInformationCreateRequest additionalPatientInformationCreateRequest) {
    
        additionalPatientInformationService.create(additionalPatientInformationCreateRequest);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @GetMapping(value = "/{patientId}")
    public  List<AdditionalPatientInformationListItemResponse>  findAllByPatientId(@PathVariable Patient patientId) {   
        return additionalPatientInformationService.findAllByPatientId(patientId);
    }
}
