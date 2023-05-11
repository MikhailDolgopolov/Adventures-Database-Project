package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.SouvenirDAO;
import com.mikhaildolgopolov.spring.database.entities.Souvenir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/souvenirs/")
public class SouvenirsController {
    @Autowired
    private SouvenirDAO souvenirDAO;

    @GetMapping(produces = "application/json")
    public List<Souvenir> getAll(){
        return souvenirDAO.findAll();
    }
    @GetMapping(path = "/for_trip/{trip_id}", produces = "application/json")
    private List<Souvenir> getForTrip(@PathVariable int trip_id){
        return souvenirDAO.findForTrip(trip_id);
    }
    @GetMapping(path = "/for_country/{country}", produces = "application/json")
    private List<Souvenir> getForCountry(@PathVariable String country){
        return souvenirDAO.findForCountry(country);
    }


    @GetMapping(path = "/for_city/{city}", produces = "application/json")
    private List<Souvenir> getForCity(@PathVariable String city){
        return souvenirDAO.findForCity(city);
    }

    @GetMapping(path = "/for_trippoint/{point_id}", produces = "application/json")
    private List<Souvenir> getForTrippoint(@PathVariable int point_id){
        return souvenirDAO.findForTrippoint(point_id);
    }

    @PostMapping(path = "/create/", consumes = "application/json")
    private void addSouvenir(@RequestBody Souvenir newSouvenir){
        souvenirDAO.save(newSouvenir);
    }
    @PostMapping(path = "/update/", consumes = "application/json")
    private void editSouvenir(@RequestBody Souvenir newSouvenir){
        souvenirDAO.update(newSouvenir);
    }

    @GetMapping(path = "/materials/", produces = "application/json")
    public List<String> getMaterials(){
        return souvenirDAO.getMaterials();
    }
    @GetMapping(path = "/types/", produces = "application/json")
    public List<String> getTypes() {
        return souvenirDAO.getTypes();
    }

    @PostMapping(value = "/delete/", consumes = "application/json")
    private void delete(@RequestBody Souvenir souvenir){
        souvenirDAO.delete(souvenir);
    }

    @GetMapping(value = "/similar_to/{id}", produces = "application/json")
    private List<Souvenir> getSimilar(@PathVariable int id){
        return souvenirDAO.findSimilarById(id);
    }
}
