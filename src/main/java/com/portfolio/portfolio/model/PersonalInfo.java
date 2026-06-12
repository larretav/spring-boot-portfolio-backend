package com.portfolio.portfolio.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

  private Long id;

  @NotBlank(message = "El nombre es obligatorio")
  private String firstName;

  @NotBlank(message = "El apellido es obligatorio")
  private String lastName;

  @NotBlank(message = "El email es obligatorio")
  private String email;

  @NotBlank(message = "El teléfono es obligatorio")
  private String phoneNumber;

  @NotBlank(message = "El título es obligatorio")
  private String title;

  @NotBlank(message = "La descripción es obligatoria")
  private String profileDescription;

  private String profileImageUrl;

  @NotBlank(message = "Los años de experiencia son obligatorios")
  private Integer yearsOfExperience;

  @NotBlank(message = "El enlace de LinkedIn es obligatorio")
  private String linkedinUrl;
  
  @NotBlank(message = "El enlace de GitHub es obligatorio")
  private String githubUrl;

}
