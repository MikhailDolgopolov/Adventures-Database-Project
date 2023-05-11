package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.*;
import com.mikhaildolgopolov.spring.database.entities.mappers.TripMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TripDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Trip> findAll(){
        return jdbcTemplate.query("SELECT * FROM main.trips ORDER BY start_date DESC", new TripMapper());
    }

    public Trip findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.trips WHERE trip_id=?",
                new TripMapper(), id).stream().findAny().orElse(null);
    }
    public Trip findByTitle(String title){
        return jdbcTemplate.query("SELECT * FROM main.trips WHERE title=?",
                new TripMapper(), title).stream().findAny().orElse(null);
    }

    public List<Trip> findByYear(int year){
        return jdbcTemplate.query("SELECT * FROM main.trips WHERE year=? ORDER BY start_date DESC",
                new TripMapper(), year);
    }
    public List<Integer> findTripYears() {
        return jdbcTemplate.queryForList("SELECT DISTINCT year FROM main.trips ORDER BY year DESC ", Integer.class);
    }
    public List<Trip> findForCountry(String name){
        String query = "SELECT DISTINCT trips.* FROM trips " +
                "JOIN trippoints t on trips.trip_id = t.trip_id " +
                "JOIN cities c on t.city = c.city " +
                "JOIN countries c2 on c2.country = c.country " +
                "WHERE c2.country=? " +
                "ORDER BY start_date DESC ";
        return jdbcTemplate.query(query, new TripMapper(), name);
    }
    public Trip update(@NotNull Trip trip){
        jdbcTemplate.update(
                "UPDATE main.trips SET " +
                        "title=?, start_date=?, end_date=?, description=?, photo_link=?" +
                        "WHERE trip_id=?",
                trip.getTitle(), trip.getStart_date(), trip.getEnd_date(),
                trip.getDescription(), trip.getPhoto_link(), trip.getTrip_id());
        return findById(trip.getTrip_id());
    }
    public Trip save(@NotNull Trip trip){
        if(trip.getTitle().equals(new Trip().getTitle()))
            return findById(-1);
        if(findByTitle(trip.getTitle())!=null) {
            update(trip);
            return findByTitle(trip.getTitle());
        }
        jdbcTemplate.update("INSERT INTO main.trips" +
                        "(title, start_date, end_date, description, photo_link)" +
                        " VALUES (?, ?, ?, ?, ?)",
                trip.getTitle(), trip.getStart_date(), trip.getEnd_date(), trip.getDescription(), trip.getPhoto_link());
        return findByTitle(trip.getTitle());
    }

    public void delete(int trip_id){
        jdbcTemplate.update("DELETE FROM main.trips WHERE trip_id=?", trip_id);
    }
    public void AddParticipants(@NotNull Trip trip, @NotNull List<Integer> list){
        String query = "INSERT INTO main.participation (trip_id, person_id) VALUES ";

        List<String> data = new ArrayList<>();
        for (int person_id : list){
            data.add(String.format("(%d, %d)", trip.getTrip_id(), person_id));
        }
        String join = StringUtils.join(data);
        query+= join.substring(1, join.length()-1);

        jdbcTemplate.update(query);
    }
    public List<Trip> findForPersonById(int personId){
        String query="SELECT T.* from main.trips as T " +
                "join main.participation p on T.trip_id = p.trip_id " +
                "join main.people p2 on p2.person_id = p.person_id " +
                "WHERE p2.person_id=? " +
                "ORDER BY T.start_date DESC ";
        return jdbcTemplate.query(query, new TripMapper(), personId);
    }
    public List<Trip> findFreeForPerson(int personId){
        String query="SELECT DISTINCT * from (SELECT * FROM main.trips EXCEPT" +
                "(SELECT T.* from main.trips as T " +
                "join main.participation p on T.trip_id = p.trip_id " +
                "join main.people p2 on p2.person_id = p.person_id " +
                "WHERE p2.person_id=? )) as otherTrips "+
                "ORDER BY start_date DESC";
        return jdbcTemplate.query(query, new TripMapper(), personId);
    }

    public void deleteParticipant(int tripId, int person){
        String query = String.format("DELETE FROM main.participation " +
                "WHERE person_id=%d and participation.trip_id=%d", person, tripId);
        jdbcTemplate.update(query);
    }

}
