package com.portfolio.portfolio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.portfolio.model.Experience;
import com.portfolio.portfolio.service.IExperienceService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
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
  public Experience getExperienceById(@PathVariable Long id) {
    var experience = experienceService.findById(id);
    if (experience.isPresent()) {
      return experience.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada");
    }
  }

  @PostMapping
  public ResponseEntity<Experience> createExperience(@RequestBody Experience entity) {
    var newExperience = experienceService.save(entity);

    return new ResponseEntity<>(newExperience, HttpStatus.CREATED);
  }

}
