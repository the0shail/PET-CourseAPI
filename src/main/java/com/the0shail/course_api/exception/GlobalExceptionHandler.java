package com.the0shail.course_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getType().name(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuth(AuthenticationException e, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(
                Instant.now(),
                HttpStatus.UNAUTHORIZED.value(),
                TypeException.INVALID_CREDENTIALS.name(),
                "Неверный email или пароль",     // ← намеренно обобщённо
                req.getRequestURI()
        ));
    }
}
