package com.library.libraryWDA.repository;

import java.util.Optional;
import java.util.UUID;

import com.library.libraryWDA.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, UUID> {
   
    
    Optional<Exam> findByCode(String code); 
    Optional<Exam> findByName(String Name); 
    @Query("select s from Exam s where s.id!=:exameId and s.code=:code")
    Optional<Exam> findByCode(UUID exameId,String code); 
    @Query("select s from Exam s where s.id!=:exameId and s.name=:name")
    Optional<Exam> findByName(UUID exameId,String name); 
}