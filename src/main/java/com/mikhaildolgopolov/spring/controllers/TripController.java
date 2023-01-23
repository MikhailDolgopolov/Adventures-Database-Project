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
    @GetMapping("/{id}")
    public String getTrip(@PathVariable int id, Model model){
        Trip trip = tripDAO.findById(id);
        model.addAttribute("trip", trip);
        return "SingleTrip";
    }

}
