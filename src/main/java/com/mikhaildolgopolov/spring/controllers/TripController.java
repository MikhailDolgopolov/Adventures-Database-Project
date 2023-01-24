package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.dao.TripPointDAO;
import com.mikhaildolgopolov.spring.database.entities.Trip;
import com.mikhaildolgopolov.spring.database.entities.TripPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trip/")
public class TripController {
    @Autowired private TripDAO tripDAO;
    @Autowired private TripPointDAO tripPointDAO;
    @GetMapping("/{id}")
    public String getTrip(@PathVariable int id, Model model){
        Trip trip = tripDAO.findById(id);
        List<TripPoint> tripPoints = tripPointDAO.findForTrip(trip);
        model.addAttribute("trip", trip);
        model.addAttribute("points", tripPoints);
        return "Trip";
    }

}
