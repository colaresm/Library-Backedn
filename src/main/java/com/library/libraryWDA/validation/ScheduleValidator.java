package com.library.libraryWDA.validation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.library.libraryWDA.dto.schedule.ScheduleCreateRequest;
import com.library.libraryWDA.exception.schedule.ScheduleExistException;
import com.library.libraryWDA.exception.schedule.ScheduleDateNotAvailable;
import com.library.libraryWDA.exception.schedule.ScheduleInProgress;
import com.library.libraryWDA.repository.ScheduleRepository;
import com.library.libraryWDA.repository.UnavailabilityDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.library.libraryWDA.model.Schedule;

@SuppressWarnings("unused")

@Component
public class ScheduleValidator {
    
 
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UnavailabilityDateRepository unavailabilityDateRepository;
    public void validateForCreation(Schedule scheduleToCreate) {
       validateDate(scheduleToCreate.getDate());
        for(Schedule schedule : scheduleRepository.findAll()) {
            if( scheduleToCreate.getProfessional().getId().equals(schedule.getProfessional().getId())  && scheduleToCreate.getDate().equals(schedule.getDate()) && scheduleToCreate.getTime().equals(schedule.getTime())    ) {
                throw new ScheduleExistException();
            } 
            if( scheduleToCreate.getPatient().getId().equals(schedule.getPatient().getId())  && scheduleToCreate.getDate().equals(schedule.getDate())  ) {
               throw new ScheduleExistException();
            }
            if(scheduleToCreate.getProfessional().getId().equals(schedule.getProfessional().getId())  && scheduleToCreate.getDate().equals(schedule.getDate()) && scheduleToCreate.getTime().isBefore(schedule.getTime().plus(schedule.getDuration()))  &&  scheduleToCreate.getTime().plus(scheduleToCreate.getDuration()).isAfter(schedule.getTime())){
                throw new ScheduleInProgress();
            }
        }
        
    }
    public void validateForUpdate(Schedule scheduleToUpdate) {
        for(Schedule schedule : scheduleRepository.findAll()) {
            if(scheduleToUpdate.getId() != schedule.getId() ){
                if( scheduleToUpdate.getProfessional().getId().equals(schedule.getProfessional().getId())  && scheduleToUpdate.getDate().equals(schedule.getDate()) && scheduleToUpdate.getTime().equals(schedule.getTime())    ) {
                    throw new ScheduleExistException();            
                } 
                if(scheduleToUpdate.getProfessional().getId().equals(schedule.getProfessional().getId())  && scheduleToUpdate.getDate().equals(schedule.getDate())  && scheduleToUpdate.getTime().isBefore(schedule.getTime().plus(schedule.getDuration()))  &&  scheduleToUpdate.getTime().plus(scheduleToUpdate.getDuration()).isAfter(schedule.getTime())){
                    throw new ScheduleInProgress();
                }   
            } 
        }
    }
    public void validateDate(LocalDate date){
        unavailabilityDateRepository.findByDate(date).ifPresent(unavailabilityDate-> {
            throw new ScheduleDateNotAvailable(); });

    }
}
