package com.library.libraryWDA.dto.anamnese;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnamneseLifeHabitListItemResponse {
    private UUID id;
    private String socialFactors;
    private String familyFactors;
}
