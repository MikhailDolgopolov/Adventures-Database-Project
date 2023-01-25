package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings({"SameReturnValue", "SpringJavaAutowiredFieldsWarningInspection"})
@Controller
@RequestMapping("/trips/")
public class TripsController {
    @Autowired
    private TripDAO tripDAO;
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("trip", new Trip());
    }
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("trips", tripDAO.findAll());
        return "Home";
    }
    @PostMapping("/post/")
    public String test(@RequestParam String number, Model model){
        return "redirect:../../trip/"+number;
    }
    @PostMapping("/add/")
    public String addTrip(@ModelAttribute Trip trip){
        return "redirect:../trips/";
    }

}
