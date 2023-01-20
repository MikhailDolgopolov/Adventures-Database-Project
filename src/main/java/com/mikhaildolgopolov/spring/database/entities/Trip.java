package com.mikhaildolgopolov.spring.database.entities;

import com.mikhaildolgopolov.spring.Transliteration;
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

    @Getter @Setter
    private String photo_link;

    public String Title(){
        return Transliteration.transliterate(title);
    }
    public String StartDate(){
        return String.valueOf(start_date);
    }
    public String EndDate(){
        return String.valueOf(start_date);
    }
    public String Description(){
        return Transliteration.transliterate(description);
    }
}
