package com.library.libraryWDA.repository;

import com.library.libraryWDA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsernameIgnoreCase(String username);
    Optional<Object> findByUsernameIgnoreCase(String username);
}
