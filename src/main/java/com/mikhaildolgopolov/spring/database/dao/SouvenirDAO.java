package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Souvenir;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.mappers.SouvenirMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SouvenirDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SouvenirDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }

    public List<Souvenir> findAll(){
        return jdbcTemplate.query("SELECT * FROM main.souvenirs", new SouvenirMapper());
    }
    public Souvenir findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.souvenirs WHERE souvenir_id=?",
                new SouvenirMapper(), id).stream().findAny().orElse(null);
    }
    public List<Souvenir> findSouvenirs(@NotNull Trip trip){
        String query = "SELECT Sv.* FROM main.souvenirs as Sv\n" +
                "join main.trip_points as TP on Sv.trip_point_id = TP.trip_point_id\n" +
                "WHERE TP.trip_id = ?\n" +
                "UNION DISTINCT\n" +
                "SELECT Sv.* FROM main.souvenirs as Sv\n" +
                "join main.cities as Ct on Sv.city = Ct.city\n" +
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
        return jdbcTemplate.query(query, new SouvenirMapper(),
                trip.getTrip_id(), trip.getTrip_id(), trip.getTrip_id());
    }
}
