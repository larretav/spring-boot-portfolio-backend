package com.portfolio.portfolio.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.portfolio.model.Experience;


public interface IExperienceRepository {
  Experience save(Experience experience);

  Optional<Experience> findById(Long id);

  List<Experience> findAll();

  void deleteById(Long id);

  List<Experience> findByPersonalInfoId(Long personalInfoId);
}
