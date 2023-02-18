package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.dao.TripPointDAO;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import com.mikhaildolgopolov.spring.helpers.PersonList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"SameReturnValue", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping("/trip/")
public class TripController {
    @Autowired private TripDAO tripDAO;
    @Autowired private TripPointDAO tripPointDAO;
    @Autowired private PersonDAO personDAO;
    @PostMapping("/participants/")
    public String addPeople(@RequestParam("trip") int tripId,
                            @ModelAttribute PersonList list,
                            Model model){
        tripDAO.AddParticipants(tripDAO.findById(tripId), list.getIntList());
        return "redirect:../"+tripId;
    }

    @PostMapping("/deletePerson/")
    public String deletePerson(@RequestParam("trip") int trip_id,
                               @RequestParam("person_id") int person_id){
        tripDAO.deleteParticipant(trip_id, person_id);
        return "redirect:../"+trip_id;
    }

}
