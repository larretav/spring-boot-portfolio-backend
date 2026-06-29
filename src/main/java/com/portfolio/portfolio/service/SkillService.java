package com.portfolio.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import com.portfolio.portfolio.exception.ValidationException;
import com.portfolio.portfolio.model.Skill;
import com.portfolio.portfolio.repository.ISkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService implements ISkillService {

  private final ISkillRepository skillRepository;
  private final Validator validator;

  @Override
  @Transactional
  public Skill save(Skill skill) {
    var result = new BeanPropertyBindingResult(skill, "skill");
    validator.validate(skill, result);

    if (result.hasErrors())
      throw new ValidationException(result);

    return skillRepository.save(skill);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Skill> findById(Long id) {
    return skillRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Skill> findAll() {
    return skillRepository.findAll();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    skillRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Skill> findByPersonalInfoId(Long personalInfoId) {
    return skillRepository.findByPersonalInfoId(personalInfoId);
  }

}
