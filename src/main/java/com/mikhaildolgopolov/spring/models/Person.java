package com.mikhaildolgopolov.spring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Entity
@Table(schema = "main", name = "people")
public class Person {
    @Id
    @Getter @Setter
    private int person_id;

    @Getter @Setter
    private String first_name;

    @Getter @Setter
    private String last_name;

    @Getter @Setter
    private String patronym;

    @Getter @Setter
    private String alias;

    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date birth_date;
}

