package com.portfolio.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.portfolio.model.Education;
import com.portfolio.portfolio.repository.IEducationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationService implements IEducationService {

  private final IEducationRepository educationRepository;

  @Override
  public Education save(Education education) {
    return educationRepository.save(education);
  }

  @Override
  public Optional<Education> findById(Long id) {
    return educationRepository.findById(id);
  }

  @Override
  public List<Education> findAll() {
    return educationRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    educationRepository.deleteById(id);
  }

  @Override
  public List<Education> findByPersonalInfoId(Long personalInfoId) {
    return educationRepository.findByPersonalInfoId(personalInfoId);
  }

}
