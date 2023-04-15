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
    @GetMapping(path = "/for_trip/{trip_id}", produces = "application/json")
    private List<Souvenir> getForTrip(@PathVariable int trip_id){
        return souvenirDAO.findForTrip(trip_id);
    }
    @GetMapping(path = "/for_trippoint/{point_id}", produces = "application/json")
    private List<Souvenir> getForTrippoint(@PathVariable int point_id){
        List<Souvenir> found=souvenirDAO.findForTrippoint(point_id);

        return found;
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
}
