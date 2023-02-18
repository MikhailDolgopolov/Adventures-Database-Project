package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.helpers.YearEntry;
import com.mikhaildolgopolov.spring.helpers.YearSplitTrips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@CrossOrigin
//@Controller
@RestController
@RequestMapping("/trips/")
public class TripsController {
    @Autowired private TripDAO tripDAO;
    @Autowired private PersonDAO personDAO;
    @GetMapping(path = "/json/", produces = "application/json")
    public List<YearEntry> getTrips(){
        return new YearSplitTrips(tripDAO.findAll()).list;
    }
    @GetMapping(path = "/list/", produces = "application/json")
    public List<Trip> justTrips(){
        return tripDAO.findAll();
    }
    @PostMapping(value = "/create/",
            consumes = "application/json", produces = "application/json")
    public Trip addTrip(@RequestBody Trip trip){
        return tripDAO.save(trip);
    }
    @PostMapping(value = "/update/",
    consumes = "application/json", produces = "application/json")
    public Trip updateTrip(@RequestBody Trip trip){
        return tripDAO.update(trip);
    }
    @PostMapping(value = "/delete/",
            consumes = "application/json")
    public String deleteTrip(@RequestBody int id){
        System.out.println(id);
        tripDAO.delete(id);
        return "Ok";
    }

    @PostMapping("/filter/")
    public List<YearEntry> setFilter(@RequestBody int id){
        if(id<1){
            return new YearSplitTrips(tripDAO.findAll()).list;
        }else{
            return new YearSplitTrips(tripDAO.findForPersonById(id)).list;
        }
    }
}
