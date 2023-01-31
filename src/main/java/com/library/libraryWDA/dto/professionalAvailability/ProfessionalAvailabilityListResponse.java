package com.library.libraryWDA.dto.professionalAvailability;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

import com.library.libraryWDA.model.enums.Weekday;
public interface ProfessionalAvailabilityListResponse {
    
    UUID getId();

    LocalTime getTime();  
 
    @Enumerated(EnumType.STRING)
    @NotNull
    Weekday getWeekday();
    
    Duration getDuration();
}
