package com.mikhaildolgopolov.spring.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CityDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CityDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
