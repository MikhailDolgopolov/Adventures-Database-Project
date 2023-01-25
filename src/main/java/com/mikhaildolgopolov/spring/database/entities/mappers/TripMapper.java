package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.Trip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripMapper implements RowMapper<Trip> {
    @Override
    public Trip mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trip trip = new Trip();
        trip.setTrip_id(rs.getInt("trip_id"));
        trip.setTitle(rs.getString("title"));
        trip.setStart_date(rs.getDate("start_date"));
        trip.setEnd_date(rs.getDate("end_date"));
        trip.setDescription(rs.getString("description"));
        trip.setPhoto_link(rs.getString("photo_link"));

        return trip;
    }
}
