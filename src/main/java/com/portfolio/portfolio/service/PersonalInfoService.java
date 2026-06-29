package com.portfolio.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import com.portfolio.portfolio.exception.ValidationException;
import com.portfolio.portfolio.model.PersonalInfo;
import com.portfolio.portfolio.repository.IPersonalInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalInfoService implements IPersonalInfoService {

  private final IPersonalInfoRepository personalInfoRepository;
  private final Validator validator;

  @Override
  @Transactional
  public PersonalInfo save(PersonalInfo personalInfo) {
    var result = new BeanPropertyBindingResult(personalInfo, "personalInfo");
    validator.validate(personalInfo, result);
    
    if (result.hasErrors())
      throw new ValidationException(result);

    return personalInfoRepository.save(personalInfo);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PersonalInfo> findById(Long id) {
    return personalInfoRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<PersonalInfo> findAll() {
    return personalInfoRepository.findAll();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    personalInfoRepository.deleteById(id);
  }

}
