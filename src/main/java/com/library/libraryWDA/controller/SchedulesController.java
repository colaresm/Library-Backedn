package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.SchedulesControllerDocs;
import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.schedule.ScheduleCalendarListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleCreateRequest;
import com.library.libraryWDA.dto.schedule.ScheduleListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleUpdateRequest;
import com.library.libraryWDA.mapper.ScheduleMapper;
import com.library.libraryWDA.model.Schedule;
import com.library.libraryWDA.service.AuthenticationService;
import com.library.libraryWDA.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/schedules")
public class SchedulesController implements  SchedulesControllerDocs{
    private final ScheduleService scheduleService;

    private final ScheduleMapper scheduleMapper;

    private final AuthenticationService authenticationService;

    @Autowired
    public SchedulesController(ScheduleService scheduleService,
                               ScheduleMapper scheduleMapper,
                               AuthenticationService authenticationService) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
        this.authenticationService = authenticationService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ScheduleCreateRequest scheduleCreateRequest) {
        scheduleService.create(scheduleCreateRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @GetMapping("/page")
    public ResponseEntity<PageResult<ScheduleListItemResponse>> getPage(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResult<ScheduleListItemResponse> pageResult = new PageResult(scheduleService.findAll(pageable));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @PutMapping
    public void update(@RequestBody @Valid ScheduleUpdateRequest scheduleUpdateRequest) {
        scheduleService.update(scheduleUpdateRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleListItemResponse> getScheduleById(@PathVariable("id") UUID id) {
        Schedule scheduleFound = scheduleService.getById(id);
        return new ResponseEntity<>(scheduleMapper.toScheduleListItemResponse(scheduleFound), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @GetMapping("/today/{patientId}")
    public ResponseEntity<ScheduleUpdateRequest> getTodayScheduleByPatientId(@PathVariable("patientId") UUID patientId) {
        return new ResponseEntity<>(scheduleService.findConfirmedAndTodayByPatientId(patientId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
    @GetMapping("/calendar/list-by-professional/{professionalId}")
    @Override
    public ResponseEntity<List<ScheduleCalendarListItemResponse>> getAllScheduleByProfessionalId(
            @PathVariable(value = "professionalId", required = false) UUID professionalId) {
        return ResponseEntity.ok(scheduleService.findAllByProfessionalId(professionalId));
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
    @GetMapping("/calendar/list-by-doctor-logged")
    @Override
    public ResponseEntity<List<ScheduleCalendarListItemResponse>> getAllScheduleByDoctorLogged() {
            return ResponseEntity.ok(scheduleService.findAllByProfessionalId(authenticationService.getProfessionalIdLogged()));
    }
}
