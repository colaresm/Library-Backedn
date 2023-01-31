package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.PatientsControllerDocs;
import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.patient.*;
import com.library.libraryWDA.mapper.PatientMapper;
import com.library.libraryWDA.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patients")
public class PatientsController implements PatientsControllerDocs {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientMapper patientMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @ModelAttribute PatientCreateRequest patientCreateRequest,
                       @RequestPart(required = false) MultipartFile profilePicture) {
        patientCreateRequest.setProfilePicture(profilePicture);
        patientService.create(patientCreateRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @PostMapping(value = "/simplified")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSimplified(@RequestBody @Valid PatientSimplifiedCreateRequest patientSimplifiedCreateRequest) {
        
        patientService.createSimplified(patientSimplifiedCreateRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @GetMapping("/page")
    public ResponseEntity<PageResult<PatientListItemResponse>> getPage(@RequestParam(required = false) String status,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResult<PatientListItemResponse> pageResult = new PageResult(patientService.findAll(status, pageable));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @GetMapping("/active")
    public ResponseEntity<List<PatientListItemResponse>> getAllActive() {
        return new ResponseEntity<>(patientService.findAllActive(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PatchMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setDeactivated(@PathVariable UUID id) {
        patientService.updateToDeactivated(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PatchMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setActivated(@PathVariable UUID id) {
        patientService.updateToActivated(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@ModelAttribute @Valid PatientUpdateRequest patientUpdateRequest,
                       @RequestPart(required = false) MultipartFile profilePicture) {
        patientUpdateRequest.setProfilePicture(profilePicture);
        patientService.update(patientUpdateRequest);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(patientMapper.toPatientResponse(patientService.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/reminders/page")
    public ResponseEntity<PageResult<PatientReminderListItemResponse>> getPage(@RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResult<PatientReminderListItemResponse> pageResult = new PageResult(patientService.findAll(pageable));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
}