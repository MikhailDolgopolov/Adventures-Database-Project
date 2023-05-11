package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.City;
import com.mikhaildolgopolov.spring.database.entities.Country;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.mappers.CityMapper;
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
        return jdbcTemplate.query("SELECT * FROM main.countries ORDER BY country", new CountryMapper());
    }

    public Country findByName(String name){
        return jdbcTemplate.query("SELECT * from main.countries WHERE country=?",
                new CountryMapper(), name).stream().findAny().orElse(null);
    }
    public City getCapital(String country){
        return jdbcTemplate.query("SELECT c.* FROM cities c " +
                "JOIN countries c2 on c.city = c2.capital_city " +
                "WHERE c.country=?", new CityMapper(), country).stream().findAny().orElse(null);
    }
    public Country findCountryByCity(String city){
        return jdbcTemplate.query("SELECT Co.* FROM countries Co " +
                        "JOIN cities c on Co.country = c.country " +
                        "WHERE c.city = ?",
                new CountryMapper(), city).stream().findAny().orElse(null);
    }
    public Country saveNew(Country country){
        String query="INSERT INTO main.countries (country, population, area)" +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(query, country.getCountry(), country.getPopulation(), country.getArea());
        return findByName(country.getCountry());
    }

    public List<City> getCities(String country){
        return jdbcTemplate.query("SELECT * FROM cities WHERE country=? ORDER BY city", new CityMapper(), country);
    }

    public List<Country> findForPerson(int id){
        String query = "(SELECT DISTINCT Cr.* FROM main.countries as Cr " +
                "join main.cities as Ct on Ct.country=Cr.country " +
                "join main.trippoints as TP on Ct.city = TP.city " +
                "join main.trips as T on TP.trip_id = T.trip_id " +
                "join main.participation as Pt on T.trip_id=Pt.trip_id " +
                "join main.people as P on Pt.person_id=? " +
                "UNION DISTINCT SELECT DISTINCT Cr.* FROM main.countries as Cr " +
                "join main.cities as Ct on Ct.country=Cr.country " +
                "join main.souvenirs as Sv on Sv.city = Ct.city " +
                "join main.trippoints as TP on Sv.trippoint_id = TP.trippoint_id " +
                "join main.trips as T on TP.trip_id = T.trip_id " +
                "join main.participation as Pt on T.trip_id=Pt.trip_id " +
                "join main.people as P on Pt.person_id=? " +
                "UNION DISTINCT SELECT DISTINCT Cr.* FROM main.countries as Cr " +
                "join main.cities as Ct on Ct.country=Cr.country " +
                "join main.sights as Si on Ct.city = Si.city " +
                "join main.visited_sights as VS on Si.sight_id = VS.sight_id " +
                "join main.trippoints as TP on VS.trippoint_id = TP.trippoint_id " +
                "join main.trips as T on TP.trip_id = T.trip_id " +
                "join main.participation as Pt on T.trip_id=Pt.trip_id " +
                "join main.people as P on Pt.person_id=? ) ORDER BY country";
        return jdbcTemplate.query(query, new CountryMapper(),
                id, id, id);
    }
    public Country update(Country country){

        jdbcTemplate.update("UPDATE countries SET population=?, area=?, capital_city=? WHERE country=?",
                country.getPopulation(), country.getArea(), country.getCapital_city(), country.getCountry());
        return findByName(country.getCountry());
    }
    public void rename(String oldName, String newName){
        jdbcTemplate.update("UPDATE countries SET country=? WHERE country=?", newName, oldName);
    }
    public void delete(String s){
        jdbcTemplate.update("DELETE FROM countries WHERE country=?", s);
    }
}
