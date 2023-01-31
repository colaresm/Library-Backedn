package com.library.libraryWDA.mapper;

import com.library.libraryWDA.dto.schedule.ScheduleCalendarListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleCreateRequest;
import com.library.libraryWDA.dto.schedule.ScheduleListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleUpdateRequest;
import com.library.libraryWDA.model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ScheduleMapper {
   
    Schedule toModel(ScheduleCreateRequest scheduleCreateRequest);
    ScheduleListItemResponse toScheduleListItemResponse(Schedule schedule);

    @Mapping(target = "professionalId", source = "professional.id")
    @Mapping(target = "patientId", source = "patient.id")
    ScheduleUpdateRequest toScheduleUpdateRequest(Schedule schedule);

    @Mapping(target = "patientName", source = "patient.name")
    @Mapping(target = "professionalName", source = "professional.name")
    ScheduleCalendarListItemResponse toScheduleCalendarList(Schedule schedule);
}
