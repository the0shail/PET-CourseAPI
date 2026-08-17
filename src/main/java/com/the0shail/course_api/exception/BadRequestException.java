package com.the0shail.course_api.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final TypeException type;

    public BadRequestException(String message, TypeException type) {
        super(message);
        this.type = type;
    }
}
