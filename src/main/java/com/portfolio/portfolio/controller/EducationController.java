package com.portfolio.portfolio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.portfolio.model.Education;
import com.portfolio.portfolio.service.IEducationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/educations")
@RequiredArgsConstructor
public class EducationController {

  private final IEducationService educationService;

  @GetMapping("/all")
  public List<Education> getAllEducations() {
    return educationService.findAll();
  }

  @GetMapping("/{id}")
  public Education getEducationById(@PathVariable Long id) {
    var education = educationService.findById(id);
    if (education.isPresent()) {
      return education.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Educación no encontrada");
    }
  }

  @PostMapping
  public ResponseEntity<Education> createEducation(@RequestBody Education entity) {
    var newEducation = educationService.save(entity);

    return new ResponseEntity<>(newEducation, HttpStatus.CREATED);
  }

}
