package com.library.libraryWDA.repository;

import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.User;
import com.library.libraryWDA.model.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByCpf(String cpf);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByCrm(String crm);

    Optional<Client> findByRg(String rg);

    Page<Client> findAllByIsActive(Boolean isActive, Pageable pageable);

    Client findByUser(User user);

    List<Client> findAllByIsActiveTrueAndPosition(Position position);

    Page<Client> findAllByPosition(Position position, Pageable pageable);
}
