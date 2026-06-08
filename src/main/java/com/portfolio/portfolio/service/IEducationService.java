package com.portfolio.portfolio.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.portfolio.model.Education;

public interface IEducationService {
  Education save(Education education);

  Optional<Education> findById(Long id);

  List<Education> findAll();

  void deleteById(Long id);

  List<Education> findByPersonalInfoId(Long personalInfoId);
}
