package com.library.libraryWDA.dto.schedule;


import com.library.libraryWDA.model.enums.SchedulesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleCalendarListItemResponse {

    private LocalDate date;

    private LocalTime time;

    private String patientName;

    private String professionalName;
    
    private Duration duration;

    private SchedulesStatus status;
}
