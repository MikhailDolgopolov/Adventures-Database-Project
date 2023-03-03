package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.CityDAO;
import com.mikhaildolgopolov.spring.database.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cities/")
public class CityController {
    @Autowired private CityDAO cityDAO;
    @GetMapping(value = "/", produces = "application/json")
    public List<City> getCities(){return cityDAO.findAll();}

    @PostMapping( value = "/create/", consumes = "application/json")
    public City addcity(@RequestBody City city){
        return cityDAO.save(city);
    }
}
