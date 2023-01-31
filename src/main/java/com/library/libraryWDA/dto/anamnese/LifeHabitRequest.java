package com.library.libraryWDA.dto.anamnese;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LifeHabitRequest {
    private String socialFactors;
    private String familyFactors;
}
