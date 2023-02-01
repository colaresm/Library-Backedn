package com.library.libraryWDA.dto.book;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {

    @NotNull(message = "O campo data de lançamento não deve ser nulo")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "O campo título do livro não deve ser nulo")
    private String title;


    @NotNull(message = "O campo autor do livro não deve ser nulo")
    private String author;


    @NotNull(message = "O campo quantidade de livros não deve ser nulo")
    private Integer quantity;
    


}
