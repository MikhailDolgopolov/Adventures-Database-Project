package com.mikhaildolgopolov.spring.helpers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mikhaildolgopolov.spring.database.entities.Sight;
import com.mikhaildolgopolov.spring.database.entities.SightVisit;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
public class SightVisitCombined {
    public int sight_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Europe/Moscow")
    public Date visited_date;
    public int trippoint_id;
    public String name;
    public String city;
    public String type;
    public int created_year;
    public String description;

    public SightVisitCombined(Sight s, SightVisit v){
        if(s.getSight_id()!=v.getSight_id()) throw new RuntimeException("Sights don't match!");
        sight_id=s.getSight_id();
        visited_date=v.getVisited_date();
        trippoint_id=v.getTrippoint_id();
        name=s.getName();
        city=s.getCity();
        type=s.getType();
        description=s.getDescription();
    }
    @JsonIgnore
    public Sight getSight(){
        Sight s = new Sight();
        s.setSight_id(sight_id);
        s.setCity(city);
        s.setName(name);
        s.setType(type);
        s.setCreated_year(created_year);
        s.setDescription(description);
        return s;
    }
    @JsonIgnore
    public SightVisit getVisit(){
        SightVisit sv = new SightVisit();
        sv.setVisited_date(visited_date);
        sv.setSight_id(sight_id);
        sv.setTrippoint_id(trippoint_id);
        return sv;
    }

}
