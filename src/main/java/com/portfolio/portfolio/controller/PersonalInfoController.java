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

import com.portfolio.portfolio.model.PersonalInfo;
import com.portfolio.portfolio.service.IPersonalInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

  private final IPersonalInfoService personalInfoService;

  public PersonalInfoController(IPersonalInfoService personalInfoService) {
    this.personalInfoService = personalInfoService;
  }

  @GetMapping("/all")
  public List<PersonalInfo> getAllPersonalInfo() {
    return personalInfoService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonalInfo> getPersonalInfoById(@PathVariable Long id) {
    var info = personalInfoService.findById(id);
    if (info.isPresent()) {
      return new ResponseEntity<>(info.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo entity) {
    var newPersonalInfo = personalInfoService.save(entity);

    return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public PersonalInfo updatePersonalInfo(@PathVariable Long id, @RequestBody PersonalInfo personalInfo) {
    personalInfo.setId(id);
    return personalInfoService.save(personalInfo);
  }

  @DeleteMapping("/{id}")
  public void deletePersonalInfo(@PathVariable Long id) {
    personalInfoService.deleteById(id);
  }

}
