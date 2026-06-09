package com.portfolio.portfolio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.portfolio.model.Experience;
import com.portfolio.portfolio.model.Skill;
import com.portfolio.portfolio.service.ISkillService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
    var skill = skillService.findById(id);
    if (skill.isPresent()) {
      return new ResponseEntity<>(skill.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/personal-info/{id}")
  public List<Skill> getSkillByPersonalInfoId(@PathVariable("id") Long personalInfoId) {
    var skills = skillService.findByPersonalInfoId(personalInfoId);
    return skills;
  }

  @PostMapping
  public ResponseEntity<Skill> createSkill(@RequestBody Skill entity) {
    var newSkill = skillService.save(entity);

    return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
    skill.setId(id);
    return skillService.save(skill);
  }

  @DeleteMapping("/{id}")
  public void deleteSkill(@PathVariable Long id) {
    skillService.deleteById(id);
  }

}
