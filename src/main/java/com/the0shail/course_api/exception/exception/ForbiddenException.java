package com.the0shail.course_api.exception.exception;

import com.the0shail.course_api.exception.TypeException;
import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {
    private final TypeException type;

    public ForbiddenException(String message, TypeException type) {
        super(message);
        this.type = type;
    }
}
