package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import com.mikhaildolgopolov.spring.database.entities.mappers.TripMapper;
import com.mikhaildolgopolov.spring.database.entities.mappers.TripPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TripPointDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TripPointDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }

    public List<TripPoint> findAll(){
        return jdbcTemplate.query("SELECT * FROM main.trippoints",
                new TripPointMapper());
    }

    public TripPoint findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.trippoints WHERE trippoint_id=?",
                new TripPointMapper(), id).stream().findAny().orElse(null);
    }
    public Trip findTrip(int id){
        return jdbcTemplate.query("SELECT * from main.trips " +
                "JOIN trippoints tp on trips.trip_id = tp.trip_id " +
                "WHERE tp.trippoint_id=?", new TripMapper(), id).stream().findAny().orElse(null);
    }
    public void delete(int id){
        TripPoint point = findById(id);
        int trip_id  =  point.getTrip_id();
        jdbcTemplate.update("DELETE FROM main.trippoints WHERE trippoint_id=?", id);
        String decreaseOrder="UPDATE main.trippoints SET trip_order=trip_order-1 WHERE trip_id=? AND trip_order>?";
        jdbcTemplate.update(decreaseOrder,trip_id, point.getTrip_order());

    }
    public List<TripPoint> findForTripById(int trip_id){
        String query = "SELECT main.trippoints.* FROM main.trippoints WHERE trip_id=? ORDER BY trip_order ASC";
        return jdbcTemplate.query(query, new TripPointMapper(), trip_id);
    }

    public List<TripPoint> save(TripPoint newPoint){
        String query = "INSERT INTO main.trippoints " +
                "(title, trip_id, city, trip_order)" +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, newPoint.getTitle(), newPoint.getTrip_id(),
                newPoint.getCity(), newPoint.getTrip_order());
        return findForTripById(newPoint.getTrip_id());
    }

    public TripPoint update(TripPoint point){
        String query = "UPDATE main.trippoints SET title=?, city=? WHERE trippoint_id=?";
        jdbcTemplate.update(query, point.getTitle(), point.getCity(), point.getTrippoint_id());
        return findById(point.getTrippoint_id());
    }
    public void swap(int point_id, int direction){
        TripPoint first,second;
        if(direction>0){
            first = findById(point_id);
            second = jdbcTemplate.query("SELECT * FROM main.trippoints WHERE trip_id=? AND trip_order=?",
                    new TripPointMapper(), first.getTrip_id(), first.getTrip_order()+1).stream().findAny().orElse(null);
            jdbcTemplate.update("UPDATE main.trippoints SET trip_order=trip_order+1 WHERE trippoint_id=?", first.getTrippoint_id());
            jdbcTemplate.update("UPDATE main.trippoints SET trip_order=trip_order-1 WHERE trippoint_id=?", second.getTrippoint_id());
        }
        else{
            first = findById(point_id);
            second = jdbcTemplate.query("SELECT * FROM main.trippoints WHERE trip_id=? AND trip_order=?",
                    new TripPointMapper(), first.getTrip_id(), first.getTrip_order()-1).stream().findAny().orElse(null);
            jdbcTemplate.update("UPDATE main.trippoints SET trip_order=trip_order-1 WHERE trippoint_id=?", first.getTrippoint_id());
            jdbcTemplate.update("UPDATE main.trippoints SET trip_order=trip_order+1 WHERE trippoint_id=?", second.getTrippoint_id());
        }
    }
}
