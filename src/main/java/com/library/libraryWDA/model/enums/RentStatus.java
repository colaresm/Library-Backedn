package com.library.libraryWDA.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RentStatus {

    DELAY("delay"),

    ONTIME("onTime"),

    RENTED("rented");

    private String value;
}
