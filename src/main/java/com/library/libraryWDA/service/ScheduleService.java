package com.library.libraryWDA.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.library.libraryWDA.dto.schedule.ScheduleCalendarListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleCreateRequest;
import com.library.libraryWDA.dto.schedule.ScheduleListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleUpdateRequest;
import com.library.libraryWDA.model.Schedule;

import com.library.libraryWDA.model.enums.SchedulesStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface ScheduleService {
    void create(ScheduleCreateRequest scheduleCreateRequest);
    void update(ScheduleUpdateRequest scheduleUpdateRequest);
    Page<ScheduleListItemResponse> findAll(Pageable pageable);
    Schedule getById(UUID id);

    List<ScheduleUpdateRequest> findAllByDateBetweenInitialDateAndFinalDate(LocalDate initialDate, LocalDate finalDate);

    List<ScheduleUpdateRequest> findAllByProfessionalIdAndTimeAndStatus(UUID professionalId, LocalTime time, SchedulesStatus schedulesStatus);

    List<ScheduleUpdateRequest> findAllByProfessionalIdAndStatus(UUID professionalId, SchedulesStatus schedulesStatus);

    ScheduleUpdateRequest findConfirmedAndTodayByPatientId(UUID patientId);

    List<ScheduleCalendarListItemResponse> findAllByProfessionalId(UUID professionalId);
}
