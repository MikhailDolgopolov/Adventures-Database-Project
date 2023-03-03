package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.CityDAO;
import com.mikhaildolgopolov.spring.database.dao.CountryDAO;
import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.dao.TripDAO;
import com.mikhaildolgopolov.spring.database.entities.City;
import com.mikhaildolgopolov.spring.database.entities.Country;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.helpers.AdventuresStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired private PersonDAO personDAO;
    @Autowired private CityDAO cityDAO;
    @Autowired private CountryDAO countryDAO;
    @Autowired private TripDAO tripDAO;
    @GetMapping("/")
    public String connection(){
        return "Ok";
    }

    @GetMapping(value = "/people/", produces = "application/json")
    public List<Person> getPeople(){
        return personDAO.findAll();
    }
    @GetMapping(value = "/statistics/", produces = "application/json")
    public AdventuresStatistics getStatistics(){
        var stats = new AdventuresStatistics();
        stats.trips=tripDAO.CountAll();
        return stats;
    }

}
