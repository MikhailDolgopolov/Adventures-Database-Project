package com.mikhaildolgopolov.spring.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CountryDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CountryDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
