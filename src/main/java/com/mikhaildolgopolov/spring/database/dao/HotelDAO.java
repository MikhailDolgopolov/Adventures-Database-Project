package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Hotel;
import com.mikhaildolgopolov.spring.database.entities.mappers.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public HotelDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }

    public List<Hotel> findAll(){
        return jdbcTemplate.query("SELECT * FROM main.hotels", new HotelMapper());
    }
    public Hotel findById(int id){
        return jdbcTemplate.query("SELECT * from main.hotels WHERE hotel_id=?",
                new HotelMapper(), id).stream().findAny().orElse(null);
    }
}
