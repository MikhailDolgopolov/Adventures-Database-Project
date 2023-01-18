package com.mikhaildolgopolov.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "main", name = "souvenirs")
public class Souvenir {
    @Id
    @Getter @Setter
    private int souvenir_id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private int trip_point_id;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private String material;

    @Getter @Setter
    private String description;
}
