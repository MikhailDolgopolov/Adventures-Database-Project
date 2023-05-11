package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.*;
import com.mikhaildolgopolov.spring.helpers.AdventuresStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("SameReturnValue")
@CrossOrigin
@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired private TripDAO tripDAO;
    @Autowired private CountryDAO countryDAO;
    @Autowired private CityDAO cityDAO;
    @Autowired private PersonDAO personDAO;
    @Autowired private SouvenirDAO souvenirDAO;
    @GetMapping("/")
    public String connection(){
        return "Ok";
    }


    @GetMapping(value = "/statistics/", produces = "application/json")
    public AdventuresStatistics getStatistics(){
        var stats = new AdventuresStatistics();
        stats.numberOfTrips=tripDAO.findAll().size();
        stats.souvenirs=souvenirDAO.findAll();
        stats.numberOfSouvenirs=stats.souvenirs.size();
        stats.cities=cityDAO.findAll();
        stats.countries=countryDAO.findAll();
        stats.people=personDAO.findAll();

        return stats;
    }

}
