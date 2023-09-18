package com.gruposalinas.msdprppreciosvtex.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StatusDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public StatusDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public String getStatus() {
    return jdbcTemplate.queryForObject("SELECT DUMMY FROM DUAL", String.class);
  }
}
