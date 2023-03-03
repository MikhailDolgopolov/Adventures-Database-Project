package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.CountryDAO;
import com.mikhaildolgopolov.spring.database.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/countries/")
public class CountryController {
    @Autowired
    CountryDAO countryDAO;
    @GetMapping(value = "/", produces = "application/json")
    public List<Country> getCountries(){
        return countryDAO.findAll();
    }
    @PostMapping( value = "/create/", consumes = "application/json")
    public Country addCountry(@RequestBody Country country){
        return countryDAO.saveNew(country);
    }
}
