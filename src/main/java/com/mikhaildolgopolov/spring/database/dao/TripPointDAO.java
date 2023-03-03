package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import com.mikhaildolgopolov.spring.database.entities.mappers.TripPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripPointDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TripPointDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }

    public List<TripPoint> findAll(){
        return jdbcTemplate.query("SELECT * FROM main.trip_points",
                new TripPointMapper());
    }

    public TripPoint findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.trip_points WHERE trip_point_id=?",
                new TripPointMapper(), id).stream().findAny().orElse(null);
    }
    public List<TripPoint> findForTripBtId(int trip_id){
        String query = "SELECT * FROM main.trip_points WHERE trip_id=? ORDER BY trip_order DESC";
        return jdbcTemplate.query(query, new TripPointMapper(), trip_id);
    }

    public List<TripPoint> save(TripPoint newPoint, int trip_id){
        String query = "INSERT INTO main.trip_points " +
                "(title, trip_id, city, trip_order)" +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, newPoint.getTitle(), newPoint.getTrip_id(),
                newPoint.getCity(), newPoint.getTrip_order());
        return findForTripBtId(trip_id);
    }
}
