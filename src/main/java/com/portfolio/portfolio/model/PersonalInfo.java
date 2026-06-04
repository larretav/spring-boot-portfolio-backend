package com.portfolio.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String title;
  private String profileDescription;
  private String profileImageUrl;
  private Integer yearsOfExperience;
  private String linkedinUrl;
  private String githubUrl;

}
