package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(schema = "main", name = "trippoints")
public class TripPoint {
    @Id
    @Getter @Setter
    private int trippoint_id;

    @Getter
    private String title;
    public void setTitle(String value){
        title=value.trim();
    }

    @Getter @Setter
    private int hotel_id;

    @Getter @Setter
    private int trip_id;

    @Getter
    private String city;
    public void setCity(String value){
        city = Optional.ofNullable(value)
                .map(String::trim)
                .orElse(null);
    }

    @Getter @Setter
    private int trip_order;

    public String toString(){
        return trip_order+". "+title;
    }
}
