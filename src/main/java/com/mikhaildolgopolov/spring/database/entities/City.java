package com.mikhaildolgopolov.spring.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;


@Entity
@Table(schema = "main", name = "Cities")
public class City {
    @Id
    @Getter
    private String city;
    public void setCity(String value){
        city = Optional.ofNullable(value)
                .map(String::trim)
                .orElse(null);
    }

    @Getter @Setter
    private String country;

    @Getter @Setter
    private int population;

    @Getter @Setter
    private int founded_year;

    @JsonIgnore
    public String toString(){
        return city+",  "+country;
    }

}
