package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripPointMapper implements RowMapper<TripPoint> {
    @Override
    public TripPoint mapRow(ResultSet rs, int rowNum) throws SQLException {
        var tripPoint = new TripPoint();
        tripPoint.setTrippoint_id(rs.getInt("trippoint_id"));
        tripPoint.setTitle(rs.getString("title"));
        tripPoint.setTrip_id(rs.getInt("trip_id"));
        tripPoint.setCity(rs.getString("city"));
        tripPoint.setTrip_order(rs.getInt("trip_order"));
        return tripPoint;
    }
}
