package com.library.libraryWDA.dto.rent;


import com.library.libraryWDA.model.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentCreateRequest {

    private UUID bookId;

    private UUID clientId;

    private LocalDate loanDate;

    private LocalDate returnDate;

    private RentStatus rentStatus;
}
