package com.library.libraryWDA.dto.query;

import java.util.UUID;

import com.library.libraryWDA.dto.anamnese.AnamneseLifeHabitListItemResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryAnamneseListItemResponse {
    private UUID id;
    private String chiefComplaint;
    private String currentHistory;
    private String revisionSystems;
    private String pathologyHistory;
    private String familyHistory; 
    private AnamneseLifeHabitListItemResponse lifeHabit;
}
