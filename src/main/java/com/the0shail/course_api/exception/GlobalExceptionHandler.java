package com.the0shail.course_api.exception;

import com.the0shail.course_api.dto.response.util.ErrorResponse;
import com.the0shail.course_api.exception.exception.BadRequestException;
import com.the0shail.course_api.exception.exception.ForbiddenException;
import com.the0shail.course_api.exception.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getType().name(),
                e.getMessage(),
                request.getRequestURI()
        ));
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleAuth(MethodArgumentNotValidException e, HttpServletRequest req) {
        String messageFieldsError = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(
                Instant.now(),
                e.getStatusCode().value(),
                TypeException.INVALID_VALUES.name(),
                messageFieldsError,
                req.getRequestURI()
        ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException e, HttpServletRequest req){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getType().name(),
                e.getMessage(),
                req.getRequestURI()
        ));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handlerForbidden(ForbiddenException e,  HttpServletRequest req){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(
                Instant.now(),
                HttpStatus.FORBIDDEN.value(),
                e.getType().name(),
                e.getMessage(),
                req.getRequestURI()
        ));
    }
}
