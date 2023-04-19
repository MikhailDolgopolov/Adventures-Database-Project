package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.CityDAO;
import com.mikhaildolgopolov.spring.database.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cities/")
public class CitiesController {
    @Autowired private CityDAO cityDAO;
    @GetMapping(value = "/", produces = "application/json")
    public List<City> getCities(){return cityDAO.findAll();}

    @PostMapping( value = "/create/", consumes = "application/json")
    public City addCity(@RequestBody City city){
        return cityDAO.save(city);
    }

    @PostMapping(value="/update/{name}", consumes="application/json")
    private City updateCity(@PathVariable String name, @RequestBody City city){
        cityDAO.rename(name, city.getCity());
        cityDAO.update(city);

        return cityDAO.findByName(city.getCity());
    }

    @PostMapping(value = "/delete/", consumes = "application/json")
    private void delete(@RequestBody String city){
        cityDAO.delete(city);
    }
}
