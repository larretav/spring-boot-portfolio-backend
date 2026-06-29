package com.portfolio.portfolio.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

  private Long id;

  @NotBlank(message = "El nombre es obligatorio")
  private String degree;

  @NotBlank(message = "La institución es obligatoria")
  private String institution;

  @NotNull(message = "La fecha de inicio es obligatoria")
  @PastOrPresent(message = "La fecha de inicio no puede ser futura")
  private LocalDate startDate;
  
  @PastOrPresent(message = "La fecha de finalización no puede ser futura")
  private LocalDate endDate;

  @NotBlank(message = "La descripción es obligatoria")
  private String description;

  private Long personalInfoId;

}
