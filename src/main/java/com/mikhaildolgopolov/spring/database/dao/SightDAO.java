package com.mikhaildolgopolov.spring.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SightDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SightDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }
}
