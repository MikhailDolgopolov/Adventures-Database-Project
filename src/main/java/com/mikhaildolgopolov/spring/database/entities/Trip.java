package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(schema = "main", name = "trips")
public class Trip implements Comparable<Trip>{
    @Id
    @Getter(AccessLevel.PUBLIC) @Setter
    private int trip_id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String title;


    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Getter(AccessLevel.PUBLIC) @Setter
    private Date start_date;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Getter(AccessLevel.PUBLIC) @Setter
    private Date end_date;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String description;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String photo_link;


    public String StartDate(){
        return String.valueOf(start_date);
    }
    public String EndDate(){
        return String.valueOf(start_date);
    }
    public String Description(){
        return String.valueOf(description);
    }
    @Override
    public String toString(){
        return String.format("%s %d", title,
                getYear());
    }
    public int getYear(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return Integer.parseInt(format.format(start_date));
    }
    public boolean hasD(){return description==null || description.isEmpty();}

    @Override
    public int compareTo(@NotNull Trip o) {
        return start_date.compareTo(o.getStart_date());
    }
}
