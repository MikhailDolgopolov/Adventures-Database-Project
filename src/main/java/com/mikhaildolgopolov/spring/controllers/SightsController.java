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
        System.out.println(sightVisit.sight_id);
        if(sightVisit.sight_id==0) {
            sightVisit.sight_id = sightDAO.save(sightVisit.getSight());
        }
        //System.out.println(sightVisit.getVisit());
        sightDAO.saveVisit(sightVisit.getVisit());

    }
    @PostMapping(value = "/update_visit/", consumes = "application/json")
    private void updateVisit(@RequestBody SightVisitCombined sightVisit){
        System.out.println("post "+sightVisit.visited_date);
        //System.out.println("no data saved");
        sightDAO.updateVisit(sightVisit.getVisit());
        sightDAO.update(sightVisit.getSight());
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
}
