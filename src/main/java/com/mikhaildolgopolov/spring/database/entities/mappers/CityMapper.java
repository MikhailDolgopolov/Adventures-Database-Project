package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();
        city.setCity(rs.getString("city"));
        city.setCountry(rs.getString("country"));
        city.setPopulation(rs.getInt("population"));
        city.setFounded_year(rs.getInt("founded_year"));

        return city;
    }
}
