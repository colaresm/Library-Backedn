package com.library.libraryWDA.dto.schedule;

import com.library.libraryWDA.model.enums.SchedulesStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleUpdateRequest {

    private UUID id;

    private LocalDate date;

    private LocalTime time;

    private UUID patientId;

    private Duration duration;

    private UUID professionalId;

    private SchedulesStatus status;
}
