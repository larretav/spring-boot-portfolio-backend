package com.portfolio.portfolio.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolio.model.Education;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EducationRepository implements IEducationRepository {

  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Education> educationRowMapper = (rs, rowNum) -> {

    var education = new Education();
    education.setId(rs.getLong("id"));
    education.setDegree(rs.getString("degree"));
    education.setInstitution(rs.getString("institution"));
    education.setStartDate(rs.getObject("start_date", LocalDate.class));
    education.setEndDate(rs.getObject("end_date", LocalDate.class));
    education.setDescription(rs.getString("description"));
    education.setPersonalInfoId(rs.getLong("personal_info_id"));

    return education;
  };

  @Override
  public Education save(Education education) {
    if (education.getId() == null) {
      var sql = "INSERT INTO educations (degree, institution, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
      var keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(connection -> {
        var ps = connection.prepareStatement(sql, new String[] { "id" });
        ps.setString(1, education.getDegree());
        ps.setString(2, education.getInstitution());
        ps.setObject(3, education.getStartDate());
        ps.setObject(4, education.getEndDate());
        ps.setString(5, education.getDescription());
        ps.setLong(6, education.getPersonalInfoId());

        return ps;
      }, keyHolder);

      education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

    } else {
      var sql = "UPDATE educations SET degree = ?, institution = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
      jdbcTemplate.update(sql,
          education.getDegree(),
          education.getInstitution(),
          education.getStartDate(),
          education.getEndDate(),
          education.getDescription(),
          education.getPersonalInfoId(),
          education.getId());
    }

    return education;
  }

  @Override
  public Optional<Education> findById(Long id) {
    try {
      String sql = "SELECT * FROM skills WHERE id = ?";
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Education> findAll() {
    var sql = "SELECT * FROM educations";
    return jdbcTemplate.query(sql, educationRowMapper);
  }

  @Override
  public void deleteById(Long id) {
    String sql = "DELETE FROM educations WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public List<Education> findByPersonalInfoId(Long personalInfoId) {
    var sql = "SELECT * FROM educations WHERE personal_info_id = ?";
    return jdbcTemplate.query(sql, educationRowMapper, personalInfoId);
  }

}
