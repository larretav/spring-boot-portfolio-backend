package com.portfolio.portfolio.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.portfolio.model.PersonalInfo;

public interface IPersonalInfoRepository {
  PersonalInfo save(PersonalInfo personalInfo);

  Optional<PersonalInfo> findById(Long id);

  List<PersonalInfo> findAll();

  void deleteById(Long id);
}
