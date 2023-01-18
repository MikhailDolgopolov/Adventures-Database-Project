package com.mikhaildolgopolov.spring.database.entities.mappers;

import com.mikhaildolgopolov.spring.database.entities.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        var p = new Person();
        p.setPerson_id(rs.getInt("person_id"));
        p.setFirst_name(rs.getString("first_name"));
        p.setLast_name(rs.getString("last_name"));
        p.setPatronym(rs.getString("patronym"));
        p.setAlias(rs.getString("alias"));
        p.setBirth_date(rs.getDate("birth_date"));
        return p;
    }
}
