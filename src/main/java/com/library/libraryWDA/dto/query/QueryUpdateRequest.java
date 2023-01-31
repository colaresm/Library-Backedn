package com.library.libraryWDA.dto.query;


import com.library.libraryWDA.dto.anamnese.AnamneseRequest;
import com.library.libraryWDA.model.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryUpdateRequest {

    private UUID id;

    private String notes;

    private String prescription;
    
    private AnamneseRequest anamnese;

    private List<Document> attachments;

    private List<UUID> exams;


}
