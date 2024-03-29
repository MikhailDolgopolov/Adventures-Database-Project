package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.City;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.mappers.CityMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CityDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<City> findAll(){
        return jdbcTemplate.query("SELECT * from main.cities ORDER BY city", new CityMapper());
    }
    public City findByName(String name){
        return jdbcTemplate.query("SELECT * FROM main.cities WHERE city=?",
                new CityMapper(), name).stream().findAny().orElse(null);
    }
    public City save(City city){
        String query = "INSERT INTO main.cities (city, country, population, founded_year)" +
                "VALUES (?,?,?,?)";
        jdbcTemplate.update(query, city.getCity(), city.getCountry(), city.getPopulation(), city.getFounded_year());
        return findByName(city.getCity());
    }

    public void update(City city){
        jdbcTemplate.update("UPDATE cities SET country=?, population=?, founded_year=? WHERE city=?",
                city.getCountry(), city.getPopulation(), city.getFounded_year(), city.getCity());
    }
    public void rename(String oldName, String newName){
        jdbcTemplate.update("UPDATE cities SET city=? WHERE city=?", newName, oldName);
    }
    public List<City> findForTrip(@NotNull Trip trip){
        String query = """
                SELECT Ct.* from main.cities as Ct
                join main.trippoints as TP on Ct.city = TP.city
                join main.trips as T on TP.trip_id = T.trip_id
                WHERE T.trip_id = ?
                UNION DISTINCT
                SELECT Ct.* from main.cities as Ct
                join main.souvenirs Sv on Ct.city = Sv.city
                join main.trippoints as TP on Sv.trippoint_id = TP.trippoint_id
                WHERE TP.trip_id = ?
                UNION DISTINCT
                SELECT Sv.* FROM main.souvenirs as Sv
                join main.cities as Ct on Sv.city = Ct.city
                join main.sights as Si on Ct.city = Si.city
                join main.visited_sights as VS on Si.sight_id = VS.sight_id
                join main.trippoints as TP on VS.trippoint_id = TP.trippoint_id
                join main.trips as T on TP.trip_id = T.trip_id
                WHERE T.trip_id=?""";
        return jdbcTemplate.query(query, new CityMapper(),
                trip.getTrip_id(), trip.getTrip_id(), trip.getTrip_id());
    }

    public void delete(String name){
        jdbcTemplate.update("DELETE FROM cities WHERE city=?", name);
    }
}
