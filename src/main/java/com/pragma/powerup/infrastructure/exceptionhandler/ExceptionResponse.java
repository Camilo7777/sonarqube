package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),

    NO_DATA_ERROR("INCORRECT DATA ENTERED"),

    PHONE_INCORRECT("NUMBER PHONE INCORRECT"),

    EMAIL_EXIST("EMAIL ALREADY EXISTS"),

    EMAIL_INCORRECT("EMAIL INCORRECT"),
    DOCUMENT_NUMBER_INCORRECT("DOCUMENT INCORRECT");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}