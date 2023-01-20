package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.Sight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SightMapper implements RowMapper<Sight> {
    @Override
    public Sight mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sight sight = new Sight();
        sight.setSight_id(rs.getInt("sight_id"));
        sight.setName(rs.getString("name"));
        sight.setCity(rs.getString("city"));
        sight.setType(rs.getString("type"));
        sight.setCreated_year(rs.getInt("created_year"));
        sight.setDescription(rs.getString("description"));

        return sight;
    }
}
