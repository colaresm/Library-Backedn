package com.library.libraryWDA.repository;

import com.library.libraryWDA.model.UnavailabilityDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface UnavailabilityDateRepository extends JpaRepository<UnavailabilityDate, UUID> {
    @Query("select s from UnavailabilityDate s where  :date BETWEEN s.initialDate AND s.finalDate")
    Optional<UnavailabilityDate> findByDate(LocalDate date);
}
