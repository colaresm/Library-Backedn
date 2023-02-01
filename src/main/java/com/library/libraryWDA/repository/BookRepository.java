package com.library.libraryWDA.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.library.libraryWDA.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {   
    Optional<Book> findByTitle(String title);
}
