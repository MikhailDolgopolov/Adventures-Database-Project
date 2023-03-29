package com.mikhaildolgopolov.spring.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(schema = "main", name = "trips")
public class Trip implements Comparable<Trip>{
    @Id
    @Getter(AccessLevel.PUBLIC) @Setter
    private int trip_id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String title;


    @Getter(AccessLevel.PUBLIC) @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date start_date;

    @Getter @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date end_date;
    @Getter(AccessLevel.PUBLIC) @Setter
    private String description;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String photo_link;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int year;


    @Override
    public String toString(){
        return String.format("%s %d", title,
                getYear());
    }

    @Override
    public int compareTo(@NotNull Trip o) {
        return start_date.compareTo(o.getStart_date());
    }
}
