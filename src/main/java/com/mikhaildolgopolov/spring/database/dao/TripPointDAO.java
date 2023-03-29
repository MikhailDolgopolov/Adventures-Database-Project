package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
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
        return jdbcTemplate.query("SELECT * FROM main.trip_points",
                new TripPointMapper());
    }

    public TripPoint findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.trip_points WHERE trip_point_id=?",
                new TripPointMapper(), id).stream().findAny().orElse(null);
    }
    public void delete(int id){
        TripPoint point = findById(id);
        int trip_id  =  point.getTrip_id();
        jdbcTemplate.update("DELETE FROM main.trip_points WHERE trip_point_id=?", id);
        String decreaseOrder="UPDATE main.trip_points SET trip_order=trip_order-1 WHERE trip_id=? AND trip_order>=?";
        jdbcTemplate.update(decreaseOrder,trip_id, point.getTrip_order());

    }
    public List<TripPoint> findForTripBtId(int trip_id){
        String query = "SELECT main.trip_points.* FROM main.trip_points WHERE trip_id=? ORDER BY trip_order ASC";
        return jdbcTemplate.query(query, new TripPointMapper(), trip_id);
    }

    public List<TripPoint> save(TripPoint newPoint){
        String query = "INSERT INTO main.trip_points " +
                "(title, trip_id, city, trip_order)" +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, newPoint.getTitle(), newPoint.getTrip_id(),
                newPoint.getCity(), newPoint.getTrip_order());
        return findForTripBtId(newPoint.getTrip_id());
    }

    public TripPoint update(TripPoint point){
        String query = "UPDATE main.trip_points SET title=?, city=? WHERE trip_point_id=?";
        jdbcTemplate.update(query, point.getTitle(), point.getCity(), point.getTrip_point_id());
        return findById(point.getTrip_point_id());
    }
    public List<TripPoint> swap(int point_id, int direction){
        TripPoint first,second;
        if(direction>0){
            first = findById(point_id);
            second = jdbcTemplate.query("SELECT * FROM main.trip_points WHERE trip_id=? AND trip_order=?",
                    new TripPointMapper(), first.getTrip_id(), first.getTrip_order()+1).stream().findAny().orElse(null);
            jdbcTemplate.update("UPDATE main.trip_points SET trip_order=trip_order+1 WHERE trip_point_id=?", first.getTrip_point_id());
            jdbcTemplate.update("UPDATE main.trip_points SET trip_order=trip_order-1 WHERE trip_point_id=?", second.getTrip_point_id());
        }
        else{
            first = findById(point_id);
            second = jdbcTemplate.query("SELECT * FROM main.trip_points WHERE trip_id=? AND trip_order=?",
                    new TripPointMapper(), first.getTrip_id(), first.getTrip_order()-1).stream().findAny().orElse(null);
            jdbcTemplate.update("UPDATE main.trip_points SET trip_order=trip_order-1 WHERE trip_point_id=?", first.getTrip_point_id());
            jdbcTemplate.update("UPDATE main.trip_points SET trip_order=trip_order+1 WHERE trip_point_id=?", second.getTrip_point_id());
        }
        return findForTripBtId(findById(point_id).getTrip_id());
    }
}
