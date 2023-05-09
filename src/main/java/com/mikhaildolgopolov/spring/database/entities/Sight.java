package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(schema = "main", name = "sights")
public class Sight {
    @Id
    @Getter @Setter
    private int sight_id;

    @Getter
    private String name;

    public void setName(String value){
        name=value.trim();
    }

    @Getter
    private String city;

    public void setCity(String value){
        city = Optional.ofNullable(value)
                .map(String::trim)
                .orElse(null);
    }

    @Getter @Setter
    private String type;

    @Getter @Setter
    private int created_year;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String image_link;
}
