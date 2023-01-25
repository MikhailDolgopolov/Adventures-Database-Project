package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.dao.TripPointDAO;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import com.mikhaildolgopolov.spring.thymeleaf.PersonList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"SameReturnValue", "SpringJavaAutowiredFieldsWarningInspection"})
@Controller
@RequestMapping("/trip/")
public class TripController {
    @Autowired private TripDAO tripDAO;
    @Autowired private TripPointDAO tripPointDAO;
    @Autowired private PersonDAO personDAO;
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("participants", new ArrayList<Person>());
        List<Person> allPeople = personDAO.findAll();
        model.addAttribute("people", new PersonList(allPeople));
    }
    @GetMapping("/{id}")
    public String getTrip(@PathVariable int id, Model model){
        Trip trip = tripDAO.findById(id);
        model.addAttribute("trip", trip);

        List<TripPoint> tripPoints = tripPointDAO.findForTrip(trip);
        model.addAttribute("points", tripPoints);

        List<Person> participants = personDAO.findForTrip(trip);
        model.addAttribute("participants", participants);

        return "Trip";
    }

    @PostMapping("/participants/")
    public String addPeople(@RequestParam("trip") int tripId,
                            @ModelAttribute PersonList list,
                            Model model){
        tripDAO.AddParticipants(tripDAO.findById(tripId), list.getIntList());
        return "redirect:../"+tripId;
    }

}
