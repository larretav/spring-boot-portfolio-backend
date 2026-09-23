package com.portfolio.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.portfolio.portfolio.exception.ValidationException;
import com.portfolio.portfolio.model.PersonalInfo;
import com.portfolio.portfolio.repository.IPersonalInfoRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonalInfoServiceTest {

  @Autowired
  private IPersonalInfoService personalInfoService;

  @Autowired
  private IPersonalInfoRepository personalInfoRepository;

  @Test
  void testSaveValidPersonalInfo() {
    var validPInfo = new PersonalInfo(null, "John", "Doe", "Software Engineer", "I'm a software engineer", null, null, null, null, null, null);
    var savedPInfo = personalInfoService.save(validPInfo);

    assertNotNull(savedPInfo.getId(), "El objeto PersonalInfo debe tener un ID asignado.");
    assertNotNull(personalInfoRepository
        .findById(savedPInfo.getId())
        .orElse(null), "El objeto PersonalInfo guardado debe existir en la base de datos.");
  }

  @Test
  void testSaveInvalidPersonalInfo() {
    var invalidPInfo = new PersonalInfo(null, "", "", "", "", null, "", "", null, "", "");
    assertThrows(ValidationException.class, () -> personalInfoService.save(invalidPInfo),
        "Debe lanzar una ValidationException cuando el nombre es vacío.");
  }

}
