package com.library.libraryWDA.validation;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityCreateRequest;
import com.library.libraryWDA.exception.availabilityProfessional.AvailabilityProfessionalExistsException;
import com.library.libraryWDA.exception.availabilityProfessional.AvailabilityProfessionalNotFoundException;
import com.library.libraryWDA.exception.professional.ProfessionalNotFoundException;
import com.library.libraryWDA.exception.query.InactivePatienteException;
import com.library.libraryWDA.model.ProfessionalAvailability;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.enums.Weekday;
import com.library.libraryWDA.repository.ProfessionalAvailabilityRepository;
import com.library.libraryWDA.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")

@Component
public class ProfessionalAvailabilityValidator {
    @Autowired
    private ProfessionalAvailabilityRepository professionalAvailabilityRepository;
    private ClientService professionalService;
    @Autowired
    public ProfessionalAvailabilityValidator(ProfessionalAvailabilityRepository professionalAvailabilityRepository, ClientService professionalService){
        this.professionalAvailabilityRepository=professionalAvailabilityRepository;
        this.professionalService=professionalService;
    }
    public void validateForCreation(ProfessionalAvailability professionalAvailability){
      

     validadeAvailability(professionalService.getById(professionalAvailability.getProfessional().getId()) ,   professionalAvailability.getWeekday(),professionalAvailability.getTime());
     validateProfessional(professionalService.getById(professionalAvailability.getProfessional().getId()).getIsActive());
    }
    public void verifyIfExistsProfessionalAvailability(UUID professionalAvailabilityId){
        ProfessionalAvailability professionalAvailability = professionalAvailabilityRepository.findById(professionalAvailabilityId)
        .orElseThrow(()-> new AvailabilityProfessionalNotFoundException(professionalAvailabilityId));

    }
    public void verifyIfExistsProfessional(UUID professionalId){
        ProfessionalAvailability availabilityProfessional = professionalAvailabilityRepository.findById(professionalId)
        .orElseThrow(()-> new ProfessionalNotFoundException(professionalId));

    }
    private void validadeAvailability(Client professionalId, Weekday weekday, LocalTime time){
        professionalAvailabilityRepository.findByProfessionalId(professionalId, weekday, time).ifPresent(exam -> {
            throw new AvailabilityProfessionalExistsException(); });

    }
    private void validateProfessional( Boolean isActive) {
        if(!isActive){throw new InactivePatienteException("Profissional invativo");}
    }
    
}
