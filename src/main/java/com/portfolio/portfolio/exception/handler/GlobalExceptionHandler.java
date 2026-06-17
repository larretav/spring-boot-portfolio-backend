package com.portfolio.portfolio.exception.handler;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.portfolio.portfolio.exception.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public String handleValidationErrors(ValidationException ex) {
    var fieldErrors = new ArrayList<String>();

    ex.getBindingResult().getFieldErrors()
        .forEach(error -> fieldErrors.add(error.getField() + ": " + error.getDefaultMessage()));

    return fieldErrors.getFirst();

  }
}
