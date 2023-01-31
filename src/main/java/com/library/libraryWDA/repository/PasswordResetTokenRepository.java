package com.library.libraryWDA.repository;

import com.library.libraryWDA.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,UUID> {


    PasswordResetToken findByCodeAndEmail(Long code, String email);

    PasswordResetToken findByEmailAndConfirmedOrChanged(String email, Boolean confirmed, Boolean changed);
}
