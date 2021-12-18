package com.reto.backend.util;

public enum Constants {
    REASON_UPDATE("actualizacion"),
    REASON_CREATION("creacion");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
