package com.library.libraryWDA.dto.professionalAvailability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

import com.library.libraryWDA.model.enums.Weekday;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalAvailabilityCreateRequest {
  
    private UUID professionalId;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Weekday weekday;

    private List<LocalTime> times;

    private Duration duration;

}
