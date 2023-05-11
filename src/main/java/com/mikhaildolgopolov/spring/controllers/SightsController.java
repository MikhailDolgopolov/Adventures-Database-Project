package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.SightDAO;
import com.mikhaildolgopolov.spring.database.entities.Sight;
import com.mikhaildolgopolov.spring.database.entities.SightVisit;
import com.mikhaildolgopolov.spring.helpers.SightVisitCombined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sights/")
public class SightsController {
    @Autowired private SightDAO sightDAO;

    @GetMapping(path = "/", produces = "application/json")
    public List<Sight> getAll(){
        return sightDAO.findAll();
    }
    @GetMapping(path = "/for_trip/{trip_id}", produces = "application/json")
    private List<SightVisitCombined> getForTrip(@PathVariable int trip_id){
        List<SightVisit> visits = sightDAO.findVisitsForTrip(trip_id);
        return getCombinedVisits(visits);
    }
    @GetMapping(path = "/for_trippoint/{point_id}", produces = "application/json")
    private List<SightVisitCombined> getForTrippoint(@PathVariable int point_id){
        List<SightVisit> visits = sightDAO.findVisitsForTripPoint(point_id);
        return getCombinedVisits(visits);
    }
    @GetMapping(path = "/for_city/{city}", produces = "application/json")
    public List<Sight> getForCity(@PathVariable String city){
        return sightDAO.findForCity(city);
    }
    @GetMapping(path = "/for_country/{country}", produces = "application/json")
    public List<Sight> getForCountry(@PathVariable String country){
        return sightDAO.findForCountry(country);
    }
    private List<SightVisitCombined> getCombinedVisits(List<SightVisit> visits){
        List<SightVisitCombined> result = new ArrayList<>();
        for (SightVisit sv : visits){
            Sight s = sightDAO.findById(sv.getSight_id());
            result.add(new SightVisitCombined(s, sv));
        }
        return result;
    }

    @PostMapping(value = "/create/", consumes = "application/json", produces = "application/json")
    private int addSight(@RequestBody Sight newSight){
        return sightDAO.save(newSight);
    }

    @PostMapping(value = "/update/", consumes = "application/json", produces = "application/json")
    private int updateSight(@RequestBody Sight newSight){
        return sightDAO.update(newSight);
    }
    @PostMapping(value = "/visit/", consumes = "application/json")
    private void visitSight(@RequestBody SightVisitCombined sightVisit){

        if(sightVisit.sight_id==0) {
            sightVisit.sight_id = sightDAO.save(sightVisit.getSight());
        }
        sightDAO.saveVisit(sightVisit.getVisit());

    }
    @PostMapping(value = "/update_visit/", consumes = "application/json")
    private void updateVisit(@RequestBody SightVisitCombined sightVisit){
        sightDAO.updateVisit(sightVisit.getVisit());
        sightDAO.update(sightVisit.getSight());
    }
    @PostMapping(value = "update_visit_date/", consumes = "application/json")
    private void updateVisitDate(@RequestBody SightVisitCombined sightVisit){
        sightDAO.updateVisit(sightVisit.getVisit());
    }
    @PostMapping(value = "/delete/", consumes = "application/json")
    private void deleteSight(@RequestBody Sight sight){
        sightDAO.delete(sight);
    }
    @PostMapping(value = "/delete_visit/{sight}", consumes = "application/json")
    private void deleteSight(@PathVariable int sight, @RequestBody int point_id){
        sightDAO.deleteVisit(sight, point_id);
    }

    @GetMapping(value = "/types/", produces = "application/json")
    private List<String> getTypes(){
        return sightDAO.getTypes();
    }

    @GetMapping(value = "/similar_to/{id}", produces = "application/json")
    private List<Sight> getSimilar(@PathVariable int id){
        return sightDAO.findSimilarById(id);
    }
}
