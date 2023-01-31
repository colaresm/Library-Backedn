package com.library.libraryWDA.repository;

import java.util.UUID;

import com.library.libraryWDA.model.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query, UUID>{

    Page<Query> findAllByPatientId(UUID patientId, Pageable pageable);
}
