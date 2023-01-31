package com.library.libraryWDA.validation;

import com.library.libraryWDA.dto.query.QueryCreateRequest;
import com.library.libraryWDA.dto.query.QueryUpdateRequest;
import com.library.libraryWDA.exception.query.InactivePatienteException;
import com.library.libraryWDA.exception.query.QueryDateNotAvailable;
import com.library.libraryWDA.repository.QueryRepository;
import com.library.libraryWDA.repository.UnavailabilityDateRepository;
import com.library.libraryWDA.service.PatientService;
import com.library.libraryWDA.service.ClientService;
import com.library.libraryWDA.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@SuppressWarnings("unused")

@Component
public class QueryValidator {

    private QueryRepository queryRepository;
    private PatientService patientService;
    private ClientService professionalService;
    private ScheduleService scheduleService;
    private UnavailabilityDateRepository unavailabilityDateRepository;

    @Autowired
    public QueryValidator(PatientService patientService,ClientService professionalService, ScheduleService scheduleService,
    UnavailabilityDateRepository unavailabilityDateRepository){
        this.patientService=patientService;
        this.professionalService=professionalService;
        this.scheduleService=scheduleService;
        this.unavailabilityDateRepository=unavailabilityDateRepository;

    }
    public void validateForCreation(QueryCreateRequest queryCreateRequest){
        validatePatient(patientService.getById(queryCreateRequest.getPatientId()).getIsActive());
        validateDate(scheduleService.getById(queryCreateRequest.getScheduleId()).getDate() );
    }

    private void validatePatient( Boolean isActive) {
        if(!isActive){throw new InactivePatienteException("Paciente invativo");}
    }
    private void validatProfessional( Boolean isActive) {
        if(!isActive){throw new InactivePatienteException("Profissional invativo");}
    }
    public void validateDate(LocalDate date){
        unavailabilityDateRepository.findByDate(date).ifPresent(unavailabilityDate-> {
            throw new QueryDateNotAvailable("Data indisponível para consulta"); });
    }
  
}
