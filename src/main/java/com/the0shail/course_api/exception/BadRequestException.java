package com.the0shail.course_api.exception;

public class BadRequestException extends RuntimeException {
    private final String message;
    private final TypeException type;

    public BadRequestException(String message, TypeException type) {
        this.message = message;
        this.type = type;
    }
}
