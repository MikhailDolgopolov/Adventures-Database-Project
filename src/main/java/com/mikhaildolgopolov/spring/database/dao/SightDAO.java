package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Sight;
import com.mikhaildolgopolov.spring.database.entities.SightVisit;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import com.mikhaildolgopolov.spring.database.entities.mappers.SightMapper;
import com.mikhaildolgopolov.spring.database.entities.mappers.SightVisitMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class SightDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SightDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }
    public List<Sight> findAll(){
        return jdbcTemplate.query("SELECT * from main.sights", new SightMapper());
    }

    public Sight findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.sights WHERE sight_id=?",
                new SightMapper(), id).stream().findAny().orElse(null);
    }
    public Sight findByName(String name){
        return jdbcTemplate.query("SELECT * FROM main.sights WHERE name=?",
                new SightMapper(), name).stream().findAny().orElse(null);
    }
    public int save(Sight newSight){

        jdbcTemplate.update("INSERT INTO main.sights (name, city, type, created_year, description) " +
                "VALUES (?, ?)", newSight.getName(), newSight.getCity(), newSight.getType(), newSight.getCreated_year(), newSight.getDescription());

        return findByName(newSight.getName()).getSight_id();
    }

    public void delete(Sight sight){
        jdbcTemplate.update("DELETE FROM sights WHERE sight_id=?", sight.getSight_id());
    }

    public int update(Sight sight){
        jdbcTemplate.update("UPDATE main.sights SET " +
                "name = ?, city = ?, type = ?, created_year=?, description=? " +
                        "WHERE sight_id=?",
                sight.getName(), sight.getCity(), sight.getType(), sight.getCreated_year(),
                sight.getDescription(), sight.getSight_id());
        return findByName(sight.getName()).getSight_id();
    }

    public void saveVisit(SightVisit v){
        jdbcTemplate.update("INSERT INTO main.visited_sights " +
                "(trippoint_id, sight_id, visited_date) VALUES " +
                "(?, ?, ?)", v.getTrippoint_id(), v.getSight_id(), v.getVisited_date());
    }
    public void updateVisit(SightVisit v){
        jdbcTemplate.update("UPDATE visited_sights SET visited_date=? WHERE sight_id=? AND visited_sights.trippoint_id=?", v.getVisited_date(), v.getSight_id(), v.getTrippoint_id());
    }
    public void deleteVisit(int sight_id, int point_id){
        jdbcTemplate.update("DELETE FROM visited_sights WHERE sight_id=? AND trippoint_id=?", sight_id, point_id);
    }
    public List<Sight> findForTrip(int trip_id){
        String query="SELECT Si.* FROM main.sights as Si " +
                "join main.visited_sights as VS on Si.sight_id = VS.sight_id " +
                "join main.trippoints as TP on VS.trippoint_id = TP.trippoint_id " +
                "join main.trips t on t.trip_id = TP.trip_id " +
                "WHERE t.trip_id = ?";
        return jdbcTemplate.query(query, new SightMapper(), trip_id);
    }
    public List<SightVisit> findVisitsForTrip(int trip_id){
        String query="SELECT v.* FROM visited_sights as v " +
                "join trippoints t on v.trippoint_id = t.trippoint_id " +
                "join trips t2 on t2.trip_id = t.trip_id " +
                "WHERE t2.trip_id=?";
        return jdbcTemplate.query(query, new SightVisitMapper(), trip_id);
    }
    public List<SightVisit> findVisitsForTripPoint(int trippoint_id){
        String query = "SELECT v.* FROM visited_sights as v " +
                "WHERE v.trippoint_id = ?";
        return jdbcTemplate.query(query, new SightVisitMapper(), trippoint_id);
    }
    public List<String> getTypes(){
        return jdbcTemplate.queryForList("SELECT DISTINCT type FROM sights WHERE type is not null", String.class);
    }

}
