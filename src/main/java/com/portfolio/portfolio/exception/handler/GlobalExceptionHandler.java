package com.portfolio.portfolio.exception.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.portfolio.portfolio.dtos.ErrorResponse;
import com.portfolio.portfolio.exception.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> handleValidationErrors(ValidationException ex) {
    Map<String, String> fieldErrors = new LinkedHashMap<>();

    ex.getBindingResult().getAllErrors().forEach(error -> {
      String field = error instanceof FieldError fe ? fe.getField() : error.getObjectName();
      fieldErrors.put(field, error.getDefaultMessage());
    });

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(400, "Validation failed", fieldErrors));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(400, ex.getMessage()));
  }

}
