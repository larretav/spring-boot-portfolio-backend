package com.portfolio.portfolio.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.portfolio.model.Experience;

public interface IExperienceService {
  Experience save(Experience experience);

  Optional<Experience> findById(Long id);

  List<Experience> findAll();

  void deleteById(Long id);

  List<Experience> findByPersonalInfoId(Long personalInfoId);
}
