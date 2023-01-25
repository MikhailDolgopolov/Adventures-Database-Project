package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.*;
import com.mikhaildolgopolov.spring.database.entities.mappers.TripMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TripDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TripDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }

    public List<Trip> findAll(){
        return jdbcTemplate.query("SELECT * FROM main.trips ORDER BY start_date DESC", new TripMapper());
    }

    public Trip findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.trips WHERE trip_id=?",
                new TripMapper(), id).stream().findAny().orElse(null);
    }
    public void update(@NotNull Trip trip){
        jdbcTemplate.update(
                "UPDATE main.trips SET " +
                        "title=?, start_date=?, end_date=?, description=?, photo_link=?" +
                        "WHERE trip_id=?",
                trip.getTitle(), trip.getStart_date(), trip.getEnd_date(),
                trip.getDescription(), trip.getPhoto_link(), trip.getTrip_id());
    }
    public void save(@NotNull Trip trip){
        if(trip.getTitle().equals(new Trip().getTitle())) return;
        jdbcTemplate.update("INSERT INTO main.trips" +
                        "(title, start_date, end_date, description, photo_link)" +
                        " VALUES (?, ?, ?, ?, ?)",
                trip.getTitle(), trip.getStart_date(), trip.getEnd_date(), trip.getDescription(), trip.getPhoto_link());
    }

    public void AddParticipants(@NotNull Trip trip, @NotNull List<Integer> list){
        String query = "INSERT INTO main.participation (trip_id, person_id) VALUES ";

        System.out.println(list.size()+",  "+Arrays.deepToString(list.toArray()));
        List<String> data = new ArrayList<>();
        for (int person_id : list){
            data.add(String.format("(%d, %d)", trip.getTrip_id(), person_id));
        }
        String join = StringUtils.join(data);
        query+= join.substring(1, join.length()-1);
        System.out.println("part qurey: "+query);
        jdbcTemplate.update(query);

    }



}
