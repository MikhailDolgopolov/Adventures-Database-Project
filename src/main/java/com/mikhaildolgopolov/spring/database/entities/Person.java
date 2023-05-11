package com.mikhaildolgopolov.spring.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(schema = "main", name = "people")
public class Person {
    @Id
    @Getter @Setter
    private int person_id;

    @Getter
    private String first_name;
    public void setFirst_name(String value){
        first_name=value.trim();
    }

    @Getter
    private String last_name;
    public void setLast_name(String value){
        last_name=value.trim();
    }

    @Getter
    private String patronym;
    public void setPatronym(String value){
        patronym=value.trim();
    }

    @Getter
    private String alias;
    public void setAlias(String value){
        alias=value.trim();
    }

    @JsonIgnore
    public String generalName(){
        if(alias.isBlank()) return last_name+" "+first_name;
        return alias;
    }
    @JsonIgnore
    @Override
    public String toString(){
        return generalName();
    }
}

