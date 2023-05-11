package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(schema = "main", name = "souvenirs")
public class Souvenir {
    @Id
    @Getter @Setter
    private int souvenir_id;

    @Getter
    private String name;
    public void setName(String value){
        name=value.trim();
    }

    @Getter @Setter
    private int trippoint_id;

    @Getter
    private String city;
    public void setCity(String value){
        city = Optional.ofNullable(value)
                .map(String::trim)
                .orElse(null);
    }

    @Getter
    private String type;
    public void setType(String value){
        if(value!=null&&value.isBlank()) type=null;
        else type=value;
    }

    @Getter
    private String material;
    public void setMaterial(String value){
        if(value!=null&&value.isBlank()) material=null;
        else material=value;
    }

    @Getter @Setter
    private String description;
}
