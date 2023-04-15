package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.SightVisit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SightVisitMapper implements RowMapper<SightVisit> {

    @Override
    public SightVisit mapRow(ResultSet rs, int rowNum) throws SQLException {
        SightVisit v = new SightVisit();
        v.setSight_id(rs.getInt("sight_id"));
        v.setVisited_date(rs.getDate("visited_date"));
        v.setTrippoint_id(rs.getInt("trippoint_id"));
        return v;
    }
}
