package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.helpers.YearSplitTrips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/trips/")
public class TripsController {
    @Autowired private TripDAO tripDAO;
    @Autowired private PersonDAO personDAO;
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("people", personDAO.findAll());
        List<Trip> list = tripDAO.findAll();

        YearSplitTrips splitTrips = new YearSplitTrips(list);
        model.addAttribute("trips",splitTrips);
        return "AllTrips";
    }
    @GetMapping(path = "/json")
    public YearSplitTrips getTrips(){
        return new YearSplitTrips(tripDAO.findAll());
    }
    @PostMapping("/post/")
    public String test(@RequestParam String number, Model model)
    {
        return "redirect:../../trip/"+number;
    }
    @PostMapping("/add/")
    public String addTrip(@ModelAttribute Trip trip){
        tripDAO.save(trip);
        return "redirect:../";
    }

    @PostMapping("/setFilter/")
    public String setFilter(@RequestParam("filter") String filter, RedirectAttributes attr){
        if(filter.equals("all")){
            YearSplitTrips trips = new YearSplitTrips(tripDAO.findAll());
            attr.addFlashAttribute("trips", trips);
        }else{
            attr.addFlashAttribute("trips",
                    new YearSplitTrips(tripDAO.findForPersonById(Integer.parseInt(filter))));
        }
        return "redirect:../filtered/";
    }
    @GetMapping("/filtered/")
    public String filteredTrips(Model model){
        return "TripGrid";
    }
}
