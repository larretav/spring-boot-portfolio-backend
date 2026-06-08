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

import com.portfolio.portfolio.model.Experience;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExperienceRepository implements IExperienceRepository {

  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Experience> experienceRowMapper = (rs, rowNum) -> {

    Experience experience = new Experience();
    experience.setId(rs.getLong("id"));
    experience.setJobTitle(rs.getString("job_title"));
    experience.setCompanyName(rs.getString("company_name"));
    experience.setStartDate(rs.getObject("start_date", LocalDate.class));
    experience.setEndDate(rs.getObject("end_date", LocalDate.class));
    experience.setDescription(rs.getString("description"));
    experience.setPersonalInfoId(rs.getLong("personal_info_id"));

    return experience;
  };

  @Override
  public Experience save(Experience experience) {
    if (experience.getId() == null) {
      var sql = "INSERT INTO experiences (job_title, company_name, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
      var keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(connection -> {
        var ps = connection.prepareStatement(sql, new String[] { "id" });
        ps.setString(1, experience.getJobTitle());
        ps.setString(2, experience.getCompanyName());
        ps.setObject(3, experience.getStartDate());
        ps.setObject(4, experience.getEndDate());
        ps.setString(5, experience.getDescription());
        ps.setLong(6, experience.getPersonalInfoId());
        return ps;
      }, keyHolder);

      experience.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

    } else {
      var sql = "UPDATE experiences SET job_title = ?, company_name = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
      jdbcTemplate.update(sql,
          experience.getJobTitle(),
          experience.getCompanyName(),
          experience.getStartDate(),
          experience.getEndDate(),
          experience.getDescription(),
          experience.getPersonalInfoId(),
          experience.getId()

      );
    }

    return experience;
  }

  @Override
  public Optional<Experience> findById(Long id) {
    try {
      String sql = "SELECT * FROM experiences WHERE id = ?";
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, experienceRowMapper, id));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Experience> findAll() {
    var sql = "SELECT * FROM experiences";
    return jdbcTemplate.query(sql, experienceRowMapper);
  }

  @Override
  public void deleteById(Long id) {
    String sql = "DELETE FROM experiences WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public List<Experience> findByPersonalInfoId(Long personalInfoId) {
    var sql = "SELECT * FROM experiences WHERE personal_info_id = ?";
    return jdbcTemplate.query(sql, experienceRowMapper, personalInfoId);
  }

}
