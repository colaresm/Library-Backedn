package com.library.libraryWDA.repository;

import com.library.libraryWDA.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByCpf(String cpf);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByRg(String rg);

    Page<Patient> findAllByIsActive(boolean isActive, Pageable pageable);

    List<Patient> findAllByIsActiveTrue();
}
