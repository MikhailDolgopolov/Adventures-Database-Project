package com.mikhaildolgopolov.spring.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TripDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TripDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }
}
