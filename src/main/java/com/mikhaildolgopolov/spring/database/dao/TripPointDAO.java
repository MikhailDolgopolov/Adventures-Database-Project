package com.mikhaildolgopolov.spring.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TripPointDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TripPointDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }
}
