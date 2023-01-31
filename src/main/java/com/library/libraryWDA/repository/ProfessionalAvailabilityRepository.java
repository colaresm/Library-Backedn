package com.library.libraryWDA.repository;



import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.library.libraryWDA.dto.professionalAvailability.ProfessionalAvailabilityListResponse;
import com.library.libraryWDA.model.ProfessionalAvailability;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.enums.Weekday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalAvailabilityRepository extends JpaRepository<ProfessionalAvailability, UUID>{
    @Query("SELECT s from ProfessionalAvailability s WHERE s.weekday=:weekday and s.time=:time and s.professional=:professionalId")
    Optional<ProfessionalAvailability> findByProfessionalId(Client professionalId, Weekday weekday,LocalTime time); 
 
   @Query("SELECT s  FROM ProfessionalAvailability s WHERE s.professional=:professionalId")
    List<ProfessionalAvailabilityListResponse> findProfessionalAvailabilityByProfessionalId(Client professionalId); 
    
    @Modifying
    @Query("DELETE FROM ProfessionalAvailability s WHERE s.professional=:professionalId")
    void deleteByProfessionalId(Client professionalId);
}
