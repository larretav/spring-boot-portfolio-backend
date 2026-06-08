package com.portfolio.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.portfolio.model.Experience;
import com.portfolio.portfolio.repository.IExperienceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExperienceService implements IExperienceService {

  private final IExperienceRepository experienceRepository;

  @Override
  public Experience save(Experience Experience) {
    return experienceRepository.save(Experience);
  }

  @Override
  public Optional<Experience> findById(Long id) {
    return experienceRepository.findById(id);
  }

  @Override
  public List<Experience> findAll() {
    return experienceRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    experienceRepository.deleteById(id);
  }

  @Override
  public List<Experience> findByPersonalInfoId(Long personalInfoId) {
    return experienceRepository.findByPersonalInfoId(personalInfoId);
  }

}
