package com.library.libraryWDA.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Prediction {
    VENTRICULAR("Ventricular"),
    NORMAL("Normal"),
    SUPRAVENTRICULAR("Supraventricular");

    private String value;
}
