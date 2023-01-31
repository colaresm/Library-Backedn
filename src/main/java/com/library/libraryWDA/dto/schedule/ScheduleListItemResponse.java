package com.library.libraryWDA.dto.schedule;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import com.library.libraryWDA.model.enums.SchedulesStatus;
import java.time.LocalTime;

import com.library.libraryWDA.model.schedule.SchedulePatientItemResponse;
import com.library.libraryWDA.model.schedule.ScheduleProfessionalItemResponse;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleListItemResponse {
   
    private UUID id;

    private LocalDate date; 
    
    private LocalTime time;  
  
    private SchedulePatientItemResponse patient;
    
    private ScheduleProfessionalItemResponse professional;
    
    private SchedulesStatus status;
}
