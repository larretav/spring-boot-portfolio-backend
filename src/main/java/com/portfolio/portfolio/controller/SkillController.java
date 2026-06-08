package com.portfolio.portfolio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.portfolio.model.Skill;
import com.portfolio.portfolio.service.ISkillService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

  private final ISkillService skillService;

  @GetMapping("/all")
  public List<Skill> getAllSkills() {
    return skillService.findAll();
  }

  @GetMapping("/{id}")
  public Skill getSkillById(@PathVariable Long id) {
    var skill = skillService.findById(id);
    if (skill.isPresent()) {
      return skill.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill no encontrada");
    }
  }

  @PostMapping
  public ResponseEntity<Skill> createSkill(@RequestBody Skill entity) {
    var newSkill = skillService.save(entity);

    return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
  }

}
