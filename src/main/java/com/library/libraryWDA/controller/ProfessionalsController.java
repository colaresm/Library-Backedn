package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.ProfessionalsControllerDocs;
import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.client.ClientCreateRequest;
import com.library.libraryWDA.dto.client.ClientDetailsResponse;
import com.library.libraryWDA.dto.client.ClientListItemResponse;
import com.library.libraryWDA.dto.client.ClientUpdateRequest;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.service.ClientService;
import com.library.libraryWDA.service.UserService;
import com.library.libraryWDA.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/Clients")
public class ProfessionalsController implements ProfessionalsControllerDocs {

    @Autowired
    private ClientService professionalService;
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@ModelAttribute @Valid ClientCreateRequest professionalCreateRequest,
                       @RequestPart(required = false) MultipartFile profilePicture) {
        professionalCreateRequest.setProfilePicture(profilePicture);
        professionalService.create(professionalCreateRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @GetMapping("/page")
    public ResponseEntity<PageResult<ClientListItemResponse>> getPage(@RequestParam(required = false) String status,
                                                                            @RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResult<ClientListItemResponse> pageResult = new PageResult(professionalService.findAll(status, pageable));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @GetMapping("/doctors/page")
    public ResponseEntity<PageResult<ClientListItemResponse>> getAllDoctors(@RequestParam(required = false) String status,
                                                                            @RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResult<ClientListItemResponse> pageResult = new PageResult(professionalService.findAllDoctors(pageable));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @GetMapping("/doctors/active")
    public ResponseEntity<List<ClientListItemResponse>> getAllActive() {
        return new ResponseEntity<>(professionalService.findAllActiveAndDoctors(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setDeactivated(@PathVariable UUID id) {
        professionalService.updateToDeactivated(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setActivated(@PathVariable UUID id) {
        professionalService.updateToActivated(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@ModelAttribute @Valid ClientUpdateRequest professionalUpdateRequest,
                       @RequestPart(required = false) MultipartFile profilePicture) {
        professionalUpdateRequest.setProfilePicture(profilePicture);
        professionalService.update(professionalUpdateRequest);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDetailsResponse> getProfessionalById(@PathVariable("id")  UUID id) {
        return new ResponseEntity<>( professionalService.getProfessionalById(id), HttpStatus.OK);
    }
}
