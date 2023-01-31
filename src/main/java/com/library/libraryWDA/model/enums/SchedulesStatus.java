package com.library.libraryWDA.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SchedulesStatus {

    CONFIRMED("Confirmed"),
    CANCELED_BY_CLINIC("CanceledByClinic"),
    CANCELED_BY_DOCTOR("CanceledByDoctor"),
    CANCELED_BY_PATIENT("CanceledByPatient"),
    UNREALIZED("Unrealized"),
    COMPLETED("Completed");

    private final String value;
}
