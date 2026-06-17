package com.portfolio.portfolio.exception;

import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

  private final BindingResult bindingResult;

  public ValidationException(BindingResult bindingResult) {
    this.bindingResult = bindingResult;
  }

}
