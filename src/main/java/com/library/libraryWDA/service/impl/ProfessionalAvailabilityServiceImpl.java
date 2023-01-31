package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityCreateRequest;
import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityListResponse;
import com.library.libraryWDA.dto.schedule.ScheduleUpdateRequest;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.ProfessionalAvailability;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.model.enums.SchedulesStatus;
import com.library.libraryWDA.repository.ProfessionalAvailabilityRepository;
import com.library.libraryWDA.service.AuthenticationService;
import com.library.libraryWDA.service.ProfessionalAvailabilityService;
import com.library.libraryWDA.service.ClientService;
import com.library.libraryWDA.service.ScheduleService;
import com.library.libraryWDA.validation.ProfessionalAvailabilityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProfessionalAvailabilityServiceImpl implements ProfessionalAvailabilityService {
  
    private final ProfessionalAvailabilityRepository professionalAvailabilityRepository;
    private final ClientService professionalService;
    private final ProfessionalAvailabilityValidator professionalAvailabilityValidator;

    private final ScheduleService scheduleService;

    private AuthenticationService authenticationService;

    @Autowired
    public ProfessionalAvailabilityServiceImpl(ProfessionalAvailabilityRepository professionalAvailabilityRepository,
                                               ClientService professionalService,
                                               ProfessionalAvailabilityValidator professionalAvailabilityValidator,
                                               ScheduleService scheduleService, AuthenticationService authenticationService) {

        this.professionalAvailabilityRepository = professionalAvailabilityRepository;
        this.professionalService = professionalService;
        this.professionalAvailabilityValidator=professionalAvailabilityValidator;
        this.scheduleService = scheduleService;
        this.authenticationService = authenticationService;
    }
    @Transactional
    public void create(ProfessionalAvailabilityCreateRequest professionalAvailabilityCreateRequest) {
     
           
        for(LocalTime time : professionalAvailabilityCreateRequest.getTimes()){
            ProfessionalAvailability availabilityProfessionalToCreate =new ProfessionalAvailability();

            Client professionalFound;

            if(authenticationService.isProfessionalLoggedWithPosition(Position.DOCTOR)) {
                professionalFound = authenticationService.getProfessionalLogged();
            } else {
                professionalFound = professionalService.getById(professionalAvailabilityCreateRequest.getProfessionalId());
            }
            availabilityProfessionalToCreate.setProfessional(professionalFound);
            availabilityProfessionalToCreate.setDuration(professionalAvailabilityCreateRequest.getDuration());
            availabilityProfessionalToCreate.setProfessional(professionalFound);
            availabilityProfessionalToCreate.setWeekday(professionalAvailabilityCreateRequest.getWeekday());
            availabilityProfessionalToCreate.setTime(time);
            professionalAvailabilityValidator.validateForCreation(availabilityProfessionalToCreate);
            setAllCanceledSchedulesToConfirmed(availabilityProfessionalToCreate, professionalFound);
            professionalAvailabilityRepository.save(availabilityProfessionalToCreate);   
        }
      
    }
    public ArrayList<String> findListProfessionalAvailability(Integer duration){
        Duration durationQuery =Duration.ofMinutes(duration);
        LocalTime startOffice = LocalTime.parse("08:00:00", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime endOffice = LocalTime.parse("17:00:00", DateTimeFormatter.ISO_LOCAL_TIME);
        ArrayList<String> availableTimes = new ArrayList<String>();


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime currentTime = startOffice;

        while (!Duration.between(currentTime, endOffice.minus(durationQuery)).isNegative() ) {
            availableTimes.add(dtf.format(currentTime));
            currentTime=currentTime.plus(durationQuery);
        }

        return availableTimes;
    } 
    public List<ProfessionalAvailabilityListResponse> findAllProfessionalAvailability(Client professionalId){
      
        return professionalAvailabilityRepository.findProfessionalAvailabilityByProfessionalId(professionalId);
    }
    public void deleteProfessionalAvailabilitylById(UUID professionalAvailabilityId) {
        professionalAvailabilityValidator.verifyIfExistsProfessionalAvailability(professionalAvailabilityId);

        ProfessionalAvailability professionalAvailabilityFound = professionalAvailabilityRepository.findById(professionalAvailabilityId).get();

        setAllConfirmedSchedulesToCanceled(professionalAvailabilityFound);

        professionalAvailabilityRepository.deleteById(professionalAvailabilityId);
    }
    @Transactional
    public void deleteProfessionalAvailabilityByProfessionalId(Client professionalId) {

        List<ScheduleUpdateRequest> foundSchedulesForCanceledByDoctor = scheduleService.findAllByProfessionalIdAndStatus(professionalId.getId(), SchedulesStatus.CONFIRMED);

        for (ScheduleUpdateRequest schedule : foundSchedulesForCanceledByDoctor) {
            if(schedule.getDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))
                    || schedule.getDate().isEqual(ChronoLocalDate.from(LocalDateTime.now()))) {
                schedule.setStatus(SchedulesStatus.CANCELED_BY_DOCTOR);
                scheduleService.update(schedule);
            }
        }

        professionalAvailabilityRepository.deleteByProfessionalId(professionalId);
    }

    private void setAllCanceledSchedulesToConfirmed(ProfessionalAvailability availabilityProfessional, Client professional){
        List<ScheduleUpdateRequest> foundSchedulesForConfirmed = scheduleService.findAllByProfessionalIdAndTimeAndStatus(professional.getId(),
                availabilityProfessional.getTime(),
                SchedulesStatus.CANCELED_BY_DOCTOR);

        for (ScheduleUpdateRequest schedule : foundSchedulesForConfirmed) {
            if(schedule.getDate().getDayOfWeek().name().equals(availabilityProfessional.getWeekday().getValue().toUpperCase())
                    && schedule.getDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))
                    || schedule.getDate().isEqual(ChronoLocalDate.from(LocalDateTime.now()))) {
                schedule.setStatus(SchedulesStatus.CONFIRMED);
                scheduleService.update(schedule);
            }
        }
    }

    private void setAllConfirmedSchedulesToCanceled(ProfessionalAvailability availabilityProfessional){
        List<ScheduleUpdateRequest> foundSchedulesForCanceledByDoctor = scheduleService.findAllByProfessionalIdAndTimeAndStatus(availabilityProfessional.getProfessional().getId(),
                availabilityProfessional.getTime(),
                SchedulesStatus.CONFIRMED);

        for (ScheduleUpdateRequest schedule : foundSchedulesForCanceledByDoctor) {
            if(schedule.getDate().getDayOfWeek().name().equals(availabilityProfessional.getWeekday().getValue().toUpperCase())
                    && schedule.getDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))
                    || schedule.getDate().isEqual(ChronoLocalDate.from(LocalDateTime.now()))) {
                schedule.setStatus(SchedulesStatus.CANCELED_BY_DOCTOR);
                scheduleService.update(schedule);
            }
        }
    }

}
