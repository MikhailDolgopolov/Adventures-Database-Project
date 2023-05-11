package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(schema = "main", name = "countries")
public class Country {
    @Id
    @Getter
    private String country;
    public void setCountry(String value){
        country=value.trim();
    }

    @Getter @Setter
    private int population;

    @Getter @Setter
    private int area;

    @Getter
    private String capital_city;
    public void setCapital_city(String value){
        capital_city = Optional.ofNullable(value)
                .map(String::trim)
                .orElse(null);
    }
}
