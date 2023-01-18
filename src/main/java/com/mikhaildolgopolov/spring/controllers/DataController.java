package com.mikhaildolgopolov.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/data/")
public class DataController {

    @GetMapping(path="/trips/")
    public String allTrips(Model model){
        return "Data";
    }

    @GetMapping("/trips/{id}")
    public String getTrip(@PathVariable("id") int id, Model model){
        return "Data";
    }

}
