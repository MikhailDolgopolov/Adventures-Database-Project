package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.CityDAO;
import com.mikhaildolgopolov.spring.database.dao.CountryDAO;
import com.mikhaildolgopolov.spring.database.entities.City;
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
    @Autowired
    CityDAO cityDAO;
    @GetMapping(value = "/", produces = "application/json")
    public List<Country> getCountries(){
        return countryDAO.findAll();
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public Country getCountry(@PathVariable String name){
        return countryDAO.findByName(name);
    }
    @GetMapping(value = "/capital/{name}", produces = "application/json")
    public City capital(@PathVariable String name){
        return countryDAO.getCapital(name);
    }
    @GetMapping(value = "/for_city/{city}", produces = "application/json")
    public Country findCountry(@PathVariable String city){
        return countryDAO.findCountryByCity(city);
    }
    @GetMapping(value = "/for_person/{id}", produces = "application/json")
    public List<Country> findCountriesForPerson(@PathVariable int id){
        return countryDAO.findForPerson(id);
    }
    @PostMapping( value = "/create/", consumes = "application/json")
    public Country addCountry(@RequestBody Country country){
        countryDAO.saveNew(country);
        City capital = new City();
        capital.setCountry(country.getCountry());
        capital.setCity(country.getCapital_city());
        cityDAO.save(capital);
        return countryDAO.update(country);
    }
    @PostMapping(value = "/update/{name}")
    private Country update(@PathVariable String name, @RequestBody Country country){
        countryDAO.rename(name, country.getCountry());
        if (cityDAO.findByName(country.getCapital_city())==null && country.getCapital_city()!=null){
            City newCity=new City();
            newCity.setCity(country.getCapital_city());
            newCity.setCountry(country.getCountry());
            cityDAO.save(newCity);
        }
        countryDAO.update(country);
        return countryDAO.findByName(country.getCountry());
    }
    @PostMapping(value = "/delete/", consumes = "application/json")
    private void deleteCountry(@RequestBody String name){
        countryDAO.delete(name);
    }
}
