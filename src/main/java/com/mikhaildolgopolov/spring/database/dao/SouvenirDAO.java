package com.mikhaildolgopolov.spring.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SouvenirDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SouvenirDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }
}
