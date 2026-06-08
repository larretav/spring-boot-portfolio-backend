package com.portfolio.portfolio.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.portfolio.model.Skill;

public interface ISkillService {
  Skill save(Skill skill);

  Optional<Skill> findById(Long id);

  List<Skill> findAll();

  void deleteById(Long id);

  List<Skill> findByPersonalInfoId(Long personalInfoId);
}
