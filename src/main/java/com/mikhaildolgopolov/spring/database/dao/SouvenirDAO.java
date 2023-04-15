package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.Souvenir;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.mappers.SouvenirMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SouvenirDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired private CityDAO cityDAO;
    @Autowired
    public SouvenirDAO (JdbcTemplate template){
        jdbcTemplate=template;
    }

    public List<Souvenir> findAll(){
        return jdbcTemplate.query("SELECT * FROM main.souvenirs", new SouvenirMapper());
    }
    public Souvenir findById(int id){
        return jdbcTemplate.query("SELECT * FROM main.souvenirs WHERE souvenir_id=?",
                new SouvenirMapper(), id).stream().findAny().orElse(null);
    }
    public List<Souvenir> findForTrip(int trip_id){
        String query = """
                SELECT Sv.* FROM main.souvenirs as Sv
                join main.trippoints as TP on Sv.trippoint_id = TP.trippoint_id
                WHERE TP.trip_id = ?
                UNION DISTINCT
                SELECT Sv.* FROM main.souvenirs as "sv"
                join main.cities as Ct on Sv.city = Ct.city
                join main.trippoints as TP on Sv.trippoint_id = TP.trippoint_id
                WHERE TP.trip_id = ?
                UNION DISTINCT
                SELECT Sv.* FROM main.souvenirs as Sv
                join main.cities as Ct on Sv.city = Ct.city
                join main.sights as Si on Ct.city = Si.city
                join main.visited_sights as VS on Si.sight_id = VS.sight_id
                join main.trippoints as TP on VS.trippoint_id = TP.trippoint_id
                join main.trips as T on TP.trip_id = T.trip_id
                WHERE T.trip_id=? ORDER BY name""";
        return jdbcTemplate.query(query, new SouvenirMapper(),
                trip_id,trip_id,trip_id);
    }

    public List<Souvenir> findForTrippoint(int point_id){
        return jdbcTemplate.query("SELECT * FROM main.souvenirs WHERE trippoint_id=?", new SouvenirMapper(), point_id);
    }

    public void save(Souvenir souvenir){
        if(cityDAO.findByName(souvenir.getName())==null)
            jdbcTemplate.update("INSERT INTO main.souvenirs " +
                "(name, trippoint_id, type, material, description) " +
                "VALUES (?, ?, ?, ?, ?)",
                souvenir.getName(), souvenir.getTrippoint_id(), souvenir.getType(),
               souvenir.getMaterial(), souvenir.getDescription());
        else{
            jdbcTemplate.update("INSERT INTO main.souvenirs " +
                            "(name, trippoint_id, city, type, material, description) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    souvenir.getName(), souvenir.getTrippoint_id(), souvenir.getCity(), souvenir.getType(),
                    souvenir.getMaterial(), souvenir.getDescription());
        }
    }

    public void update(Souvenir s){
        jdbcTemplate.update("UPDATE souvenirs SET " +
                "name=?, trippoint_id=?, city=?, type=?, material=?, description=? " +
                "WHERE souvenir_id="+ s.getSouvenir_id(), s.getName(), s.getTrippoint_id(), s.getCity(), s.getType(),
                s.getMaterial(), s.getDescription());
    }
    public void delete (Souvenir souvenir){
        jdbcTemplate.update("DELETE FROM souvenirs WHERE souvenir_id=?", souvenir.getSouvenir_id());
    }
    public List<String> getTypes(){
        return jdbcTemplate.queryForList("SELECT DISTINCT type from souvenirs WHERE type is not null", String.class);
    }
    public List<String> getMaterials(){
        return jdbcTemplate.queryForList("SELECT DISTINCT material from souvenirs where material is not null", String.class);
    }

}
