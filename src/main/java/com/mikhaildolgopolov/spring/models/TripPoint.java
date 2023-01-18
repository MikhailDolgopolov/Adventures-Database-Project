package com.mikhaildolgopolov.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "main", name = "trip_points")
public class TripPoint {
    @Id
    @Getter @Setter
    private int trip_point_id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private int hotel_id;

    @Getter @Setter
    private int trip_id;

    @Getter @Setter
    private String city;
}
