package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "main", name = "sights")
public class Sight {
    @Id
    @Getter @Setter
    private int sight_id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private int created_year;

    @Getter @Setter
    private String description;
}
