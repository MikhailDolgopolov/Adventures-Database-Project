package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.TripPointDAO;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/trippoints/")
public class TripPointsController {
    @Autowired
    TripPointDAO tripPointDAO;
    @GetMapping(value = "/", produces = "application/json")
    public List<TripPoint> get(){
        return tripPointDAO.findAll();
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public TripPoint getById(@PathVariable int id){
        return tripPointDAO.findById(id);
    }
    @GetMapping(value = "/for_trip/{trip}",
            produces = "application/json")
    public List<TripPoint> getTripPoints(@PathVariable("trip") int trip_id){
        return tripPointDAO.findForTripById(trip_id);
    }
    @PostMapping(value = "/create/", consumes = "application/json")
    public void create(@RequestBody TripPoint tp){
        if(tp.getCity()!=null && tp.getCity().length()==0) tp.setCity(null);
        tripPointDAO.save(tp);
    }
    @PostMapping(value = "/update/",
            consumes = "application/json", produces = "application/json")
    public TripPoint updatePoint(@RequestBody TripPoint point){
        if(point.getCity()!=null && point.getCity().length()==0) point.setCity(null);
        return tripPointDAO.update(point);
    }

    @PostMapping(value = "/delete/", consumes = "application/json")
    public void deletePoint(@RequestBody int point_id){
        tripPointDAO.delete(point_id);
    }

    @PostMapping(value = "/reorder/{point}", consumes = "application/json")
    public void reorderPoints(@PathVariable int point, @RequestBody int direction){
        tripPointDAO.swap(point, direction);
    }
}
