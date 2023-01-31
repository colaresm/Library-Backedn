package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityCreateRequest;
import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityListResponse;
import com.library.libraryWDA.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


 
public interface ProfessionalAvailabilityService {
   
    void create(ProfessionalAvailabilityCreateRequest professionalAvailabilityCreateRequest);
    List<ProfessionalAvailabilityListResponse> findAllProfessionalAvailability(Client professionalId);
    ArrayList<String> findListProfessionalAvailability(Integer duration);
    void deleteProfessionalAvailabilitylById(UUID professionalAvailabilityId);
    void deleteProfessionalAvailabilityByProfessionalId(Client professionalId);

}
