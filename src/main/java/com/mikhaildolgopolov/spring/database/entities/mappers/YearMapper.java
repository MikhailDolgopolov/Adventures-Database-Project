package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.Hotel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class YearMapper implements RowMapper<Integer> {
    @Override
    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("year");
    }
}
