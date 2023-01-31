package com.library.libraryWDA.dto.query;

import com.library.libraryWDA.dto.schedule.ScheduleListItemResponse;
import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.model.Exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryListItemResponse {

    private UUID id;

    private String notes;

    private String prescription;
     
    private ScheduleListItemResponse schedule;

    private QueryAnamneseListItemResponse anamnese;

    private Document document;
    
    private List<Document> attachments;

    private List<Exam> exams;
}
