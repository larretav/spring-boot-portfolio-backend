package com.portfolio.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import com.portfolio.portfolio.model.PersonalInfo;
import com.portfolio.portfolio.repository.IPersonalInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalInfoService implements IPersonalInfoService {

  private final IPersonalInfoRepository personalInfoRepository;
  private final Validator validator;

  @Override
  public PersonalInfo save(PersonalInfo personalInfo) {
    var result = new BeanPropertyBindingResult(personalInfo, "personalInfo");
    validator.validate(personalInfo, result);
    if (result.hasErrors()) {
      System.out.println("Errores encontrados: " + result.getAllErrors());
      throw new IllegalArgumentException("Los datos proporcionados no son válidos");
    }

    return personalInfoRepository.save(personalInfo);
  }

  @Override
  public Optional<PersonalInfo> findById(Long id) {
    return personalInfoRepository.findById(id);
  }

  @Override
  public List<PersonalInfo> findAll() {
    return personalInfoRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    personalInfoRepository.deleteById(id);
  }

}
