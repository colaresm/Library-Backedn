package com.library.libraryWDA.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListItemResponse {

    private UUID id;
   
    private LocalDate releaseDate;


    private String title;


    private String author;


    private Integer quantity;
}
