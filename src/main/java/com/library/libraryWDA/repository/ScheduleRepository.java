package com.library.libraryWDA.repository;

import com.library.libraryWDA.model.Schedule;
import com.library.libraryWDA.model.enums.SchedulesStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public  interface ScheduleRepository extends JpaRepository<Schedule, UUID>  {
   
    List<Schedule> findAllByDateBetween(LocalDate initialDate, LocalDate finalDate);

    List<Schedule> findAllByProfessionalIdAndTimeAndStatus(UUID professionalId, LocalTime time, SchedulesStatus schedulesStatus);

    List<Schedule> findAllByProfessionalIdAndStatus(UUID professionalId, SchedulesStatus schedulesStatus);

    Optional<Schedule> findScheduleByStatusAndDateAndPatientId(SchedulesStatus schedulesStatus, LocalDate date, UUID patientId);

    List<Schedule> findAllByProfessionalId(UUID professionalId);
}
