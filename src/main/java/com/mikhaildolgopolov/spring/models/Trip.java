package com.mikhaildolgopolov.spring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(schema = "main", name = "trips")
public class Trip {
    @Id
    @Getter @Setter
    private int trip_id;

    @Getter @Setter
    private String title;

    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date start_date;

    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date end_date;

    @Getter @Setter
    private String description;
}
