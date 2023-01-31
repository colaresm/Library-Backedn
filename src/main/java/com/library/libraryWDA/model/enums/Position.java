package com.library.libraryWDA.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Position {
    ADMIN("Admin"),
    ATTENDANT("Attendant"),
    DOCTOR("Doctor");

    private String value;

    public String getRole() {
        return "ROLE_" + value.toUpperCase();
    }
}
