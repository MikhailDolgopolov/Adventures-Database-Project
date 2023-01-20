package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trip/")
public class TripController {
    @Autowired
    private TripDAO tripDAO;
    @PostMapping("/{id}")
    @ModelAttribute("Title")
    public String postTrip(@RequestParam("id") int id, Model model){
       return "redirect://trip/"+id;
    }
    @GetMapping("/{id}")
    public String getTrip(@PathVariable int id, Model model){
        Trip trip = tripDAO.findById(id);
        model.addAttribute("Title", trip.Title());
        model.addAttribute("Trip", trip);
        return "SingleTrip";
    }

}
