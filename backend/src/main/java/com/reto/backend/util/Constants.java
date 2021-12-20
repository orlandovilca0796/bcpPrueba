package com.reto.backend.util;

public enum Constants {
    REASON_UPDATE("actualizacion"),
    REASON_CREATION("creacion"),
    ERROR_NO_INSERT_CURRENCY("Ya existe una moneda con el nombre: "),
    ERROR_CREATE_EXCHANGE_RATE_DUPLICATE("Ya existe un tipo de cambio registrado."),
    ERROR_UPDATE_EXCHANGE_RATE_NEED_ID("Falta mencionar el id del tipo de cambio para actualizar");

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
