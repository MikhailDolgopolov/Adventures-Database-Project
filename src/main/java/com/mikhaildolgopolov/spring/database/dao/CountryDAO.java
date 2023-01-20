package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Country;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.mappers.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CountryDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Country> findAll(){
        return jdbcTemplate.query("SELECT * FROM main.countries", new CountryMapper());
    }

    public Country findById(String name){
        return jdbcTemplate.query("SELECT * from main.countries WHERE country=?",
                new CountryMapper(), name).stream().findAny().orElse(null);
    }
    public List<Country> findForPerson(Person person){
        String query = "SELECT Cr.* FROM main.countries as Cr " +
                "join main.cities as Ct on Ct.country=Cr.country " +
                "join main.trip_points as TP on Ct.city = TP.city " +
                "join main.trips as T on TP.trip_id = T.trip_id " +
                "join main.participation as Pt on T.trip_id=Pt.trip_id " +
                "join main.people as P on Pt.person_id=?" +
                "UNION DISTINCT\n" +
                "SELECT Cr.* FROM main.countries as Cr " +
                "join main.cities as Ct on Ct.country=Cr.country " +
                "join main.souvenirs as Sv on Sv.city = Ct.city "+
                "join main.trip_points as TP on Sv.trip_point_id = TP.trip_point_id " +
                "join main.trips as T on TP.trip_id = T.trip_id " +
                "join main.participation as Pt on T.trip_id=Pt.trip_id " +
                "join main.people as P on Pt.person_id=?" +
                "UNION DISTINCT\n" +
                "SELECT Cr.* FROM main.countries as Cr " +
                "join main.cities as Ct on Ct.country=Cr.country " +
                "join main.souvenirs as Sv on Sv.city = Ct.city " +
                "join main.sights as Si on Ct.city = Si.city\n" +
                "join main.visited_sights as VS on Si.sight_id = VS.sight_id\n" +
                "join main.trip_points as TP on VS.trip_point_id = TP.trip_point_id\n" +
                "join main.trips as T on TP.trip_id = T.trip_id\n" +
                "join main.participation as Pt on T.trip_id=Pt.trip_id " +
                "join main.people as P on Pt.person_id=?";
        return jdbcTemplate.query(query, new CountryMapper(),
                person.getPerson_id(), person.getPerson_id(), person.getPerson_id());
    }
}
