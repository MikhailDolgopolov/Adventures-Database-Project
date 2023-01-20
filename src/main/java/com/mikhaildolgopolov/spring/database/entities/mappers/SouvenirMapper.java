package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.Souvenir;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SouvenirMapper implements RowMapper<Souvenir> {
    @Override
    public Souvenir mapRow(ResultSet rs, int rowNum) throws SQLException {
        Souvenir souvenir = new Souvenir();
        souvenir.setSouvenir_id(rs.getInt("souvenir_id"));
        souvenir.setName(rs.getString("name"));
        souvenir.setTrip_point_id(rs.getInt("trip_point_id"));
        souvenir.setCity(rs.getString("city"));
        souvenir.setType(rs.getString("type"));
        souvenir.setMaterial(rs.getString("material"));
        souvenir.setDescription(rs.getString("description"));

        return souvenir;
    }
}
