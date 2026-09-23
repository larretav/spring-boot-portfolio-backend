package com.portfolio.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.portfolio.portfolio.exception.ValidationException;
import com.portfolio.portfolio.model.Skill;
import com.portfolio.portfolio.repository.ISkillRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SkillServiceTest {

  @Autowired
  private ISkillService skillService;

  @Autowired
  private ISkillRepository skillRepository;

  @Test
  void testSaveValidSkill() {
    var validSkill = new Skill(null, "Java", 50, "fa-java", 1L);
    var savedSkill = skillService.save(validSkill);

    assertNotNull(savedSkill.getId(), "El objeto Skill debe tener un ID asignado.");
    assertNotNull(skillRepository
        .findById(savedSkill.getId())
        .orElse(null), "El objeto Skill guardado debe existir en la base de datos.");
  }

  @Test
  void testSaveInvalidSkill() {
    var invalidSkill = new Skill(null, "", 90, "fa-java", 1L);
    assertThrows(ValidationException.class, ()-> skillService.save(invalidSkill), "Debe lanzar una ValidationException cuando el nombre es vacío.");
  }

}
