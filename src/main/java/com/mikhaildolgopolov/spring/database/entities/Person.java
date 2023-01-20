package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Date;

@Entity
@Table(schema = "main", name = "people")
public class Person {
    @Id
    @Getter @Setter
    private int person_id;

    @Getter @Setter
    private String first_name;

    @Getter @Setter
    private String last_name;

    @Getter @Setter
    private String patronym;

    @Getter @Setter
    private String alias;

    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date birth_date;

    public String SqlFields(boolean includeId){
        String result = String.format("%s, %s, %s, %s, %s",
                first_name, last_name, patronym, alias, birth_date);
        if (includeId){
            result = person_id+", "+result;
        }
        return result;
    }
}

