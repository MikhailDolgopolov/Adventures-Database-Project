package com.mikhaildolgopolov.spring.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class HotelDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public HotelDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }
}
