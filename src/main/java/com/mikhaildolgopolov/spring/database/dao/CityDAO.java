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
        return jdbcTemplate.query("SELECT * from main.cities", new CityMapper());
    }
    public City findById(String name){
        return jdbcTemplate.query("SELECT * FROM main.cities WHERE city=?",
                new CityMapper(), name).stream().findAny().orElse(null);
    }
    public List<City> findForTrip(@NotNull Trip trip){
        String query = "SELECT Ct.* from main.cities as Ct\n" +
                "join main.trip_points as TP on Ct.city = TP.city\n" +
                "join main.trips as T on TP.trip_id = T.trip_id\n" +
                "WHERE T.trip_id = ?\n" +
                "UNION DISTINCT\n" +
                "SELECT Ct.* from main.cities as Ct\n" +
                "join main.souvenirs Sv on Ct.city = Sv.city\n" +
                "join main.trip_points as TP on Sv.trip_point_id = TP.trip_point_id\n" +
                "WHERE TP.trip_id = ?\n" +
                "UNION DISTINCT\n" +
                "SELECT Sv.* FROM main.souvenirs as Sv\n" +
                "join main.cities as Ct on Sv.city = Ct.city\n" +
                "join main.sights as Si on Ct.city = Si.city\n" +
                "join main.visited_sights as VS on Si.sight_id = VS.sight_id\n" +
                "join main.trip_points as TP on VS.trip_point_id = TP.trip_point_id\n" +
                "join main.trips as T on TP.trip_id = T.trip_id\n" +
                "WHERE T.trip_id=?";
        return jdbcTemplate.query(query, new CityMapper(),
                trip.getTrip_id(), trip.getTrip_id(), trip.getTrip_id());
    }
}
