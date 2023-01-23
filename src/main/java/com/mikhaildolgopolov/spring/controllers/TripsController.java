package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trips/")
public class TripsController {
    @Autowired
    private TripDAO tripDAO;
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("trips", tripDAO.findAll());
        return "AllTrips";
    }
    @PostMapping("/post/")
    public String test(@RequestParam String number, Model model){
        return "redirect://localhost:8080/trip/"+number;
    }
    @GetMapping("/get/{number}")
    public String getTrip(@PathVariable String number){
        System.out.println("number");
        return "AllTrips";
    }

}
