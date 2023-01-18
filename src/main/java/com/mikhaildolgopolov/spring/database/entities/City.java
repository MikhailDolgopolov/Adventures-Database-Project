package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(schema = "main", name = "Cities")
public class City {
    @Id
    @Getter @Setter
    private String city;

    @Getter @Setter
    private String country;

    @Getter @Setter
    private int population;

    @Getter @Setter
    private int founded_year;

}
