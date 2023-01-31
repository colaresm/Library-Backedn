package com.library.libraryWDA.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamListItemResponse {

    private UUID id;

    private String code;

    private String name;
}
