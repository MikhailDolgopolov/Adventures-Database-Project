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
    @GetMapping("/test/")
    public String test(Model model){
        model.addAttribute("trips", tripDAO.findAll());
        return "TableTest";
    }

}
