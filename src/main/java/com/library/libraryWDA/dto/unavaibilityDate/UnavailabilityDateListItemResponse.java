package com.library.libraryWDA.dto.unavaibilityDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnavailabilityDateListItemResponse {

    private UUID id;

    private String name;

    private LocalDate initialDate;

    private LocalDate finalDate;
}
