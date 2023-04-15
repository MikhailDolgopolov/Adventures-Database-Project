package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.dao.TripPointDAO;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/trips/")
public class TripsController {
    @Autowired private TripDAO tripDAO;
    @Autowired private TripPointDAO tripPointDAO;
    @Autowired private PersonDAO personDAO;
    @GetMapping(path = "/years/{year}", produces = "application/json")
    public List<Trip> getTrips(@PathVariable int year){
        return tripDAO.findByYear(year);
    }

    @GetMapping(path = "/years/", produces = "application/json")
    public List<Integer> getYears(){

        return tripDAO.findTripYears();
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Trip> justTrips(){
        return tripDAO.findAll();
    }
    @PostMapping(value = "/create/",
            consumes = "application/json", produces = "application/json")
    public Trip addTrip(@RequestBody Trip trip){
        return tripDAO.save(trip);
    }
    @PostMapping(value = "/delete/",
            consumes = "application/json")
    public void deleteTrip(@RequestBody int id){
        tripDAO.delete(id);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public Trip get(@PathVariable int id){
        return tripDAO.findById(id);
    }
    @PostMapping(value = "/update/", consumes = "application/json", produces = "application/json")
    public Trip updateTrip(@RequestBody Trip trip){
        return tripDAO.update(trip);
    }
    @GetMapping(value = "/{trip}/participants/", produces = "application/json")
    public List<Person> getParticipants(@PathVariable("trip") int trip){
        return personDAO.findForTripById(trip);
    }
    @PostMapping(value = "/{trip}/participants/add/", produces = "application/json")
    public List<Person> addPeople(@PathVariable("trip") int tripId,
                                  @RequestBody List<Integer> list){
        tripDAO.AddParticipants(tripDAO.findById(tripId), list);
        return personDAO.findForTripById(tripId);
    }

    @PostMapping(value = "/{trip}/participants/delete/",
            consumes = "application/json", produces = "application/json")
    public List<Person> deletePerson(@PathVariable("trip") int trip_id,
                                     @RequestBody int person_id){
        tripDAO.deleteParticipant(trip_id, person_id);
        return personDAO.findForTripById(trip_id);
    }



    @PostMapping(value = "/{trip}/trippoints/create/",
            produces = "application/json")
    public List<TripPoint> addTripPoint(@PathVariable("trip") int trip_id,
                                        @RequestBody TripPoint point){
        point.setTrip_id(trip_id);
        return tripPointDAO.save(point);
    }
}
