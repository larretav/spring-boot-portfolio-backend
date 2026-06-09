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

import com.portfolio.portfolio.model.Education;
import com.portfolio.portfolio.model.Experience;
import com.portfolio.portfolio.service.IExperienceService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceController {

  private final IExperienceService experienceService;

  @GetMapping("/all")
  public List<Experience> getAllExperiences() {
    return experienceService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Experience> getExperienceById(@PathVariable Long id) {
    var experience = experienceService.findById(id);
    if (experience.isPresent()) {
      return new ResponseEntity<>(experience.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Experience> createExperience(@RequestBody Experience entity) {
    var newExperience = experienceService.save(entity);

    return new ResponseEntity<>(newExperience, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public Experience updateExperience(@PathVariable Long id, @RequestBody Experience experience) {
    experience.setId(id);
    return experienceService.save(experience);
  }

  @DeleteMapping("/{id}")
  public void deleteExperience(@PathVariable Long id) {
    experienceService.deleteById(id);
  }
}
