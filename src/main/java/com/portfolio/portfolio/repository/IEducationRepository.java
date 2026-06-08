package com.portfolio.portfolio.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.portfolio.model.Education;

public interface IEducationRepository {
  Education save(Education skill);

  Optional<Education> findById(Long id);

  List<Education> findAll();

  void deleteById(Long id);

  List<Education> findByPersonalInfoId(Long personalInfoId);
}
