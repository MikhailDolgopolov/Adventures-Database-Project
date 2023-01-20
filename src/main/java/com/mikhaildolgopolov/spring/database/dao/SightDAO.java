package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Sight;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import com.mikhaildolgopolov.spring.database.entities.mappers.SightMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SightDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SightDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }
    public List<Sight> findAll(){
        return jdbcTemplate.query("SELECT * from main.sights", new SightMapper());
    }

    public Sight findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.sights WHERE sight_id=?",
                new SightMapper(), id).stream().findAny().orElse(null);
    }
    public List<Sight> findForTrip(@NotNull Trip trip){
        String query="SELECT Si.* FROM main.sights as Si " +
                "join main.visited_sights as VS on Si.sight_id = VS.sight_id " +
                "join main.trip_points as TP on VS.trip_point_id = TP.trip_point_id " +
                "join trips t on t.trip_id = TP.trip_id " +
                "WHERE t.trip_id = ?";
        return jdbcTemplate.query(query, new SightMapper(), trip.getTrip_id());
    }
    public List<Sight> findForTripPoint(@NotNull TripPoint tripPoint){
        String query = "SELECT Si.* from main.sights as Si " +
                "join main.visited_sights as VS on Si.sight_id = VS.sight_id " +
                "join main.trip_points as TP on VS.trip_point_id = TP.trip_point_id " +
                "WHERE TP.trip_point_id = ?";
        return jdbcTemplate.query(query, new SightMapper(), tripPoint.getTrip_point_id());
    }

}
