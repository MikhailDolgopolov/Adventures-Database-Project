package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.dao.TripPointDAO;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import com.mikhaildolgopolov.spring.helpers.PersonList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings({"SameReturnValue", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping("/trip/")
@CrossOrigin
public class TripController {
    @Autowired private TripDAO tripDAO;
    @Autowired private TripPointDAO tripPointDAO;
    @Autowired private PersonDAO personDAO;

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

    @GetMapping(value = "/{trip}/trip-points/",
            produces = "application/json")
    public List<TripPoint> getTripPoints(@PathVariable("trip") int trip_id){
        return tripPointDAO.findForTripBtId(trip_id);
    }

    @PostMapping(value = "/{trip}/trip-points/create/",
            produces = "application/json")
    public List<TripPoint> addTripPoint(@PathVariable("trip") int trip_id,
                                        @RequestBody TripPoint point){
        return tripPointDAO.save(point, trip_id);
    }
}
