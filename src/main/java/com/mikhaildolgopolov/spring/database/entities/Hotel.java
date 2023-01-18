package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "main", name = "hotels")
public class Hotel {
    @Id
    @Getter @Setter
    private int hotel_id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private int stars;

    @Getter @Setter
    private String link;
}
