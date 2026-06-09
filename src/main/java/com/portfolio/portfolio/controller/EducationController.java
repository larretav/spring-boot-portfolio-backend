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
import com.portfolio.portfolio.model.PersonalInfo;
import com.portfolio.portfolio.service.IEducationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
    var education = educationService.findById(id);
    if (education.isPresent()) {
      return new ResponseEntity<>(education.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Education> createEducation(@RequestBody Education entity) {
    var newEducation = educationService.save(entity);

    return new ResponseEntity<>(newEducation, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public Education updateEducation(@PathVariable Long id, @RequestBody Education education) {
    education.setId(id);
    return educationService.save(education);
  }

  @DeleteMapping("/{id}")
  public void deleteEducation(@PathVariable Long id) {
    educationService.deleteById(id);
  }
}
