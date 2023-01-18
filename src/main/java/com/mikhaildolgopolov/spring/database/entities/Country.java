package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "main", name = "countries")
public class Country {
    @Id
    @Getter @Setter
    private String country;

    @Getter @Setter
    private int population;

    @Getter @Setter
    private int area;

    @Getter @Setter
    private String capital_city;
}
