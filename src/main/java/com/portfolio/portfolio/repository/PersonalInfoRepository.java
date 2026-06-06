package com.portfolio.portfolio.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolio.model.PersonalInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepository implements IPersonalInfoRepository {

  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, rowNum) -> {

    PersonalInfo personalInfo = new PersonalInfo();
    personalInfo.setId(rs.getLong("id"));
    personalInfo.setFirstName(rs.getString("first_name"));
    personalInfo.setLastName(rs.getString("last_name"));
    personalInfo.setEmail(rs.getString("email"));
    personalInfo.setPhoneNumber(rs.getString("phone_number"));
    personalInfo.setTitle(rs.getString("title"));
    personalInfo.setProfileDescription(rs.getString("profile_description"));
    personalInfo.setProfileImageUrl(rs.getString("profile_image_url"));
    personalInfo.setYearsOfExperience(rs.getInt("years_of_experience"));
    personalInfo.setLinkedinUrl(rs.getString("linkedin_url"));
    personalInfo.setGithubUrl(rs.getString("github_url"));
    return personalInfo;
  };

  @Override
  public PersonalInfo save(PersonalInfo personalInfo) {
    if (personalInfo.getId() == null) {
      String sql = "INSERT INTO personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone_number, linkedin_url, github_url) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      var keyHolder = new GeneratedKeyHolder();

      jdbcTemplate.update(connection -> {
        var ps = connection.prepareStatement(sql, new String[] { "id" });
        ps.setString(1, personalInfo.getFirstName());
        ps.setString(2, personalInfo.getLastName());
        ps.setString(3, personalInfo.getTitle());
        ps.setString(4, personalInfo.getProfileDescription());
        ps.setString(5, personalInfo.getProfileImageUrl());
        if (personalInfo.getYearsOfExperience() == null) {
          ps.setInt(6, personalInfo.getYearsOfExperience());
        } else {
          ps.setNull(6, java.sql.Types.INTEGER);
        }
        ps.setString(7, personalInfo.getEmail());
        ps.setString(8, personalInfo.getPhoneNumber());
        ps.setString(9, personalInfo.getLinkedinUrl());
        ps.setString(10, personalInfo.getGithubUrl());
        return ps;
      }, keyHolder);

      personalInfo.setId(Objects.requireNonNull(keyHolder.getKey().longValue()));
      // jdbcTemplate.update(sql,
      // personalInfo.getFirstName(),
      // personalInfo.getLastName(),
      // personalInfo.getTitle(),
      // personalInfo.getProfileDescription(),
      // personalInfo.getProfileImageUrl(),
      // personalInfo.getYearsOfExperience(),
      // personalInfo.getEmail(),
      // personalInfo.getPhoneNumber(),
      // personalInfo.getLinkedinUrl(),
      // personalInfo.getGithubUrl());
      // return personalInfo;
    } else {
      String sql = "UPDATE personal_info SET first_name = ?, last_name = ?, title = ?, profile_description = ?, profile_image_url = ?, years_of_experience = ?, email = ?, phone_number = ?, linkedin_url = ?, github_url = ? "
          + "WHERE id = ?";
      jdbcTemplate.update(sql,
          personalInfo.getFirstName(),
          personalInfo.getLastName(),
          personalInfo.getTitle(),
          personalInfo.getProfileDescription(),
          personalInfo.getProfileImageUrl(),
          personalInfo.getYearsOfExperience(),
          personalInfo.getEmail(),
          personalInfo.getPhoneNumber(),
          personalInfo.getLinkedinUrl(),
          personalInfo.getGithubUrl(),
          personalInfo.getId());
    }
    return personalInfo;

  }

  // Opcion 1
  // @Override
  // public Optional<PersonalInfo> findById(Long id) {
  // String sql = "SELECT * FROM personal_info WHERE id = ?";
  // return jdbcTemplate.query(sql, personalInfoRowMapper).stream().findFirst();
  // }
  @Override
  public Optional<PersonalInfo> findById(Long id) {
    try {
      String sql = "SELECT * FROM personal_info WHERE id = ?";
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<PersonalInfo> findAll() {
    String sql = "SELECT * FROM personal_info";
    return jdbcTemplate.query(sql, personalInfoRowMapper);
  }

  @Override
  public void deleteById(Long id) {
    String sql = "DELETE * FROM personal_info WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }

}
