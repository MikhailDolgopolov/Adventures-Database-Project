package com.mikhaildolgopolov.spring.database.dao;

import com.mikhaildolgopolov.spring.database.entities.City;
import com.mikhaildolgopolov.spring.database.entities.Country;
import com.mikhaildolgopolov.spring.database.entities.Souvenir;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import com.mikhaildolgopolov.spring.database.entities.mappers.CityMapper;
import com.mikhaildolgopolov.spring.database.entities.mappers.CountryMapper;
import com.mikhaildolgopolov.spring.database.entities.mappers.SouvenirMapper;
import com.mikhaildolgopolov.spring.database.entities.mappers.TripPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Country findCountry(int souvenir_id){
        String query="SELECT c.* FROM countries c JOIN cities c2 on c.country = c2.country " +
                "JOIN souvenirs s on c2.city = s.city " +
                "WHERE s.souvenir_id=?" +
                "UNION " +
                "SELECT c.* FROM countries c JOIN cities c2 on c.country = c2.country " +
                "JOIN trippoints t on c2.city = t.city " +
                "JOIN souvenirs s2 on t.trippoint_id = s2.trippoint_id " +
                "WHERE s2.souvenir_id=?";
        return jdbcTemplate.query(query, new CountryMapper(), souvenir_id, souvenir_id).stream().findAny().orElse(null);
    }
    public City findCity(int souvenir_id){
        String query="SELECT c.* FROM cities c " +
                "JOIN souvenirs s on c.city = s.city " +
                "WHERE s.souvenir_id=?" +
                "UNION " +
                "SELECT c.* FROM cities c " +
                "JOIN trippoints t on c.city = t.city " +
                "JOIN souvenirs s2 on t.trippoint_id = s2.trippoint_id " +
                "WHERE s2.souvenir_id=?";
        return jdbcTemplate.query(query, new CityMapper(), souvenir_id, souvenir_id).stream().findAny().orElse(null);
    }
    public TripPoint findTrippointById(int souv_id){
        return jdbcTemplate.query("SELECT Tp.* FROM trippoints Tp " +
                "JOIN souvenirs s on Tp.trippoint_id = s.trippoint_id " +
                "WHERE souvenir_id="+souv_id, new TripPointMapper()).stream().findAny().orElse(null);
    }
    public List<TripPoint> findTrippointsForSouvenirTrip(int souv_id){
        return jdbcTemplate.query("SELECT Tp.* FROM trippoints Tp " +
                "JOIN trips t on t.trip_id = Tp.trip_id " +
                "WHERE t.trip_id IN (SELECT t.trip_id FROM trips t " +
                "JOIN trippoints t2 on t.trip_id = t2.trip_id " +
                "JOIN souvenirs s on t2.trippoint_id = s.trippoint_id " +
                "WHERE souvenir_id=?)", new TripPointMapper(),souv_id);
    }
    public List<Souvenir> findSimilarById(int s_id){
        Country c = findCountry(s_id);
        if(c==null) return new ArrayList<>();
        Souvenir current=findById(s_id);
        if(current==null) return new ArrayList<>();
        return jdbcTemplate.query("SELECT Sv.* FROM souvenirs Sv " +
                        "join trippoints t on t.trippoint_id = Sv.trippoint_id " +
                        "join cities c on c.city = t.city " +
                        "WHERE c.country=? or type=? and material=?" +
                        "UNION DISTINCT " +
                        "SELECT Sv.* FROM souvenirs Sv " +
                        "join cities c on c.city = Sv.city " +
                        "WHERE c.country=? or type=? and material=?",
                new SouvenirMapper(),
                c.getCountry(),  current.getType(), current.getMaterial(),
                c.getCountry(), current.getType(), current.getMaterial());
    }

    public List<Souvenir> findForCountry(String country){
        String query = "SELECT Sv.* FROM souvenirs Sv " +
                "join trippoints t on t.trippoint_id = Sv.trippoint_id " +
                "join cities c on c.city = t.city " +
                "WHERE c.country=?" +
                "UNION " +
                "SELECT Sv.* FROM souvenirs Sv " +
                "join cities c on c.city = Sv.city " +
                "WHERE c.country=?";
        return jdbcTemplate.query(query, new SouvenirMapper(), country,country);
    }

    public List<Souvenir> findForCity(String city){
        String query = "SELECT Sv.* FROM souvenirs Sv " +
                "join trippoints t on t.trippoint_id = Sv.trippoint_id " +
                "WHERE Sv.city = ? or t.city=?";
        return jdbcTemplate.query(query, new SouvenirMapper(), city, city);
    }

    public void save(Souvenir s){
        if(cityDAO.findByName(s.getName())==null)
            jdbcTemplate.update("INSERT INTO main.souvenirs " +
                "(name, trippoint_id, type, material, description, city) " +
                "VALUES (?, ?, ?, ?, ?,?)",
                s.getName(), s.getTrippoint_id(), s.getType(),
               s.getMaterial(), s.getDescription(), s.getCity());
        else{
            jdbcTemplate.update("INSERT INTO main.souvenirs " +
                            "(name, trippoint_id, city, type, material, description) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    s.getName(), s.getTrippoint_id(), s.getCity(), s.getType(),
                    s.getMaterial(), s.getDescription());
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
