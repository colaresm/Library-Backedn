package com.library.libraryWDA.controller.docs;

import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.schedule.ScheduleCalendarListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleCreateRequest;
import com.library.libraryWDA.dto.schedule.ScheduleListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Api("Schedules management")
public interface SchedulesControllerDocs {
    @ApiOperation("Schedule creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success schedule creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or schedule already registered on system")
    })
    void create(ScheduleCreateRequest scheduleCreateRequest);

    @ApiOperation("List all registered schedules")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return all registered schedules"),
    })
    ResponseEntity<PageResult<ScheduleListItemResponse>> getPage(int page, int size);

    @ApiOperation(value = "Update schedule operation")
    void update(ScheduleUpdateRequest scheduleUpdateRequest);

    @ApiOperation("Return registered schedule by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedule returned successfully"),
            @ApiResponse(code = 404, message = "Schedule not found")
    })
    ResponseEntity<ScheduleListItemResponse> getScheduleById(UUID id);

    @ApiOperation("Return of today's confirmed schedule by patient id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedule returned successfully"),
            @ApiResponse(code = 404, message = "Schedule not found")
    })
    ResponseEntity<ScheduleUpdateRequest> getTodayScheduleByPatientId(UUID patientId);

    @ApiOperation("Return list all schedules by professional id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List all schedules returned successfully"),
            @ApiResponse(code = 404, message = "Professional not found")
    })
    ResponseEntity<List<ScheduleCalendarListItemResponse>> getAllScheduleByProfessionalId(UUID professionalId);

    @ApiOperation("Return list all schedules by doctor logged")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List all schedules returned successfully"),
    })
    ResponseEntity<List<ScheduleCalendarListItemResponse>> getAllScheduleByDoctorLogged();
}
