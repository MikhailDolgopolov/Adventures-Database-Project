package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();
        country.setCountry(rs.getString("country"));
        country.setArea(rs.getInt("area"));
        country.setPopulation(rs.getInt("population"));
        country.setCapital_city(rs.getString("capital_city"));

        return country;
    }
}
