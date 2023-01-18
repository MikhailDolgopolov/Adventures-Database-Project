package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> GetAll(){
        return jdbcTemplate.query("SELECT * FROM people", new PersonMapper());
    }
    public Person GetPerson(int id){
        return jdbcTemplate.query("SELECT * FROM people WHERE person_id=?", new Object[]{id},
                new PersonMapper()).stream().findAny().orElse(null);
    }

}
