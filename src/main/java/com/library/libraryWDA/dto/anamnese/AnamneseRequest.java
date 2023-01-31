package com.library.libraryWDA.dto.anamnese;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnamneseRequest {
    private String chiefComplaint;
    private String currentHistory;
    private String revisionSystems;
    private String pathologyHistory;
    private String familyHistory; 
    private LifeHabitRequest lifeHabit;
}
