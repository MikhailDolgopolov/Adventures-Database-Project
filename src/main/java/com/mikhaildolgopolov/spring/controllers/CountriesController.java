package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.CountryDAO;
import com.mikhaildolgopolov.spring.database.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/countries/")
public class CountriesController {
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
    @PostMapping(value = "/update/{name}")
    private Country update(@PathVariable String name, @RequestBody Country country){
        countryDAO.rename(name, country.getCountry());
        countryDAO.update(country);
        return countryDAO.findByName(country.getCountry());
    }
    @PostMapping(value = "/delete/", consumes = "application/json")
    private void deleteCountry(@RequestBody String name){
        countryDAO.delete(name);
    }
}
