package com.library.libraryWDA.dto.client;

import com.library.libraryWDA.model.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientListItemResponse {

    private UUID id;

    private String name;

    private String cpf;

    private Position position;

    private String email;

    private String firstPhone;

    private LocalDate admission;

    private Boolean isActive;
}
