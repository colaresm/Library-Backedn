package com.library.libraryWDA.dto.query;

import com.library.libraryWDA.dto.anamnese.AnamneseRequest;
import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.model.Exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCreateRequest {

    private String notes;

    private String prescription;

    @NotNull
    private UUID patientId;

    @NotNull
    private UUID scheduleId;
    
    private AnamneseRequest anamnese;

    private List<Document> attachments;

    private List<UUID> exams;
}
