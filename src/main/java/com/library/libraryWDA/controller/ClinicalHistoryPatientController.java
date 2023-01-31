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

import com.library.libraryWDA.controller.docs.ClinicalHistoryPatientControllerDocs;
import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientCreateRequest;
import com.library.libraryWDA.dto.clinicalHistoryPatient.ClinicalHistoryPatientListItemResponse;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.service.ClinicalHistoryPatientService;


@RestController
@RequestMapping(value = "/clinical-history-patient")
public class ClinicalHistoryPatientController implements ClinicalHistoryPatientControllerDocs {
    private final ClinicalHistoryPatientService clinicalHistoryPatientService;
    @Autowired
    public ClinicalHistoryPatientController(ClinicalHistoryPatientService clinicalHistoryPatientService) {
        this.clinicalHistoryPatientService = clinicalHistoryPatientService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ClinicalHistoryPatientCreateRequest clinicalHistoryPatientCreateRequest) {
    
        clinicalHistoryPatientService.create(clinicalHistoryPatientCreateRequest);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @GetMapping(value = "/{patientId}")
    public  List<ClinicalHistoryPatientListItemResponse>  findAllByPatientId(@PathVariable Patient patientId) {   
        return clinicalHistoryPatientService.findAllByPatientId(patientId);
    }
}
