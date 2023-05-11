package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.dao.SightDAO;
import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.Trip;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/trips/")
public class TripsController {
    @Autowired private TripDAO tripDAO;
    @Autowired private SightDAO sightDAO;
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

    @GetMapping(path = "/for_country/{country}", produces = "application/json")
    public List<Trip> getTripsForCountry(@PathVariable String country){
        return tripDAO.findForCountry(country);
    }

    @GetMapping(path = "/for_person/{id}", produces = "application/json")
    public List<Trip> getTripsForPerson(@PathVariable int id){
        return tripDAO.findForPersonById(id);
    }
    @GetMapping(path = "/not_mine/{id}", produces = "application/json")
    public List<Trip> getOtherTrips(@PathVariable int id){
        return tripDAO.findFreeForPerson(id);
    }

    @GetMapping(path = "/for_sight/{sight_id}", produces = "application/json")
    private List<Trip> getTripsConnectedToSight(@PathVariable int sight_id){
        return sightDAO.findTripsForSight(sight_id);
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

    @GetMapping(value = "/{id}", produces = "application/json")
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
}
