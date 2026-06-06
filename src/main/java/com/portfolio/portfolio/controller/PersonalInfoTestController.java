package com.portfolio.portfolio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.portfolio.model.PersonalInfo;
import com.portfolio.portfolio.service.IPersonalInfoService;

@RestController
@RequestMapping("/api/test-personal-info")
public class PersonalInfoTestController {

  private final IPersonalInfoService personalInfoService;

  public PersonalInfoTestController(IPersonalInfoService personalInfoService) {
    this.personalInfoService = personalInfoService;
  }

  @GetMapping("/all")
  public List<PersonalInfo> getAll() {
    return personalInfoService.findAll();
  }

  @GetMapping("/{id}")
  public String getById(Long id) {
    var info = personalInfoService.findById(id);
    if (info.isPresent()) {
      return info.get().toString();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inforamción personal no encontrada");
    }
  }

}
