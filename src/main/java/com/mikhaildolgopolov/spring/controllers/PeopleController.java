package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/people/")
public class PeopleController {
    @Autowired
    private PersonDAO personDAO;
    @GetMapping(value = "/", produces = "application/json")
    public List<Person> getPeople(){
        return personDAO.findAll();
    }

    @PostMapping(value = "/update/", consumes = "application/json")
    public void updatePerson(@RequestBody Person p){
        personDAO.update(p);
    }
    @PostMapping(value = "/delete/", consumes = "application/json")
    public void deletePerson(@RequestBody int id){
        personDAO.delete(id);
    }
}
