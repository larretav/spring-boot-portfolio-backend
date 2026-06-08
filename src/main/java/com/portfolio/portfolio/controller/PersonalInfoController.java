package com.portfolio.portfolio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.portfolio.model.PersonalInfo;
import com.portfolio.portfolio.service.IPersonalInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
  public PersonalInfo getPersonalInfoById(@PathVariable Long id) {
    var info = personalInfoService.findById(id);
    if (info.isPresent()) {
      return info.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inforamción personal no encontrada");
    }
  }

  @PostMapping
  public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo entity) {
    var newPersonalInfo = personalInfoService.save(entity);

    return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
  }

}
