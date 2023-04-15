package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.PersonDAO;
import com.mikhaildolgopolov.spring.database.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
