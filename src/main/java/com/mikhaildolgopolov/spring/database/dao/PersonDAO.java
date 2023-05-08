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
    public List<Person> findAll(){

        return jdbcTemplate.query("SELECT * FROM main.people ORDER BY last_name, first_name", new PersonMapper());
    }
    public Person findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.people WHERE person_id=?",
                new PersonMapper(), id).stream().findAny().orElse(null);
    }
    public void save(Person p){
        jdbcTemplate.update("INSERT INTO people (first_name, last_name, patronym, alias)" +
                " VALUES (?, ?, ?, ?)", p.getFirst_name(), p.getLast_name(), p.getPatronym(), p.getAlias());
    }
    public void update(Person p){
        jdbcTemplate.update("UPDATE main.people SET first_name=?, last_name=?, patronym=?, alias=?" +
                " WHERE person_id=?", p.getFirst_name(), p.getLast_name(), p.getPatronym(),
                p.getAlias(), p.getPerson_id());
    }
    public void delete(int person_id){
        jdbcTemplate.update("DELETE FROM people WHERE person_id=?", person_id);
    }
    public List<Person> findForTripById(int tripId){
        return jdbcTemplate.query("SELECT * FROM main.people " +
                "join main.participation as P on main.people.person_id=P.person_id " +
                "WHERE P.trip_id=?", new PersonMapper(),tripId);
    }

}
