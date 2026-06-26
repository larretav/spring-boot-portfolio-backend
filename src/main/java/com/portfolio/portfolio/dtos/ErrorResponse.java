package com.portfolio.portfolio.dtos;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorResponse {
  private int status;
  private String message;
  private LocalDateTime timestamp;
  private Map<String, String> errors; // para errores de validación por campo

  // Constructor para errores generales
  public ErrorResponse(int status, String message) {
    this.status = status;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }

  // Constructor para errores de validación
  public ErrorResponse(int status, String message, Map<String, String> errors) {
    this(status, message);
    this.errors = errors;
  }

  
}