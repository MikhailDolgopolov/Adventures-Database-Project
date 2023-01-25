package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.mappers.PersonMapper;
import org.jetbrains.annotations.NotNull;
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
    public List<Person> findAll(){

        return jdbcTemplate.query("SELECT * FROM main.people", new PersonMapper());
    }
    public Person findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.people WHERE person_id=?",
                new PersonMapper(), id).stream().findAny().orElse(null);
    }
    public List<Person> findForTrip(@NotNull Trip trip){
        return jdbcTemplate.query("SELECT * FROM main.people " +
                "join main.participation as P on main.people.person_id=P.person_id " +
                "WHERE P.trip_id=?", new PersonMapper(), trip.getTrip_id());
    }

}
