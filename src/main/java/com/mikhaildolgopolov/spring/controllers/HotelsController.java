package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.dao.HotelDAO;
import com.mikhaildolgopolov.spring.database.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/hotels/")
public class HotelsController {
    @Autowired
    private HotelDAO hotelDAO;
    @GetMapping(value = "/", produces = "application/json")
    public List<Hotel> getHotels(){
        return hotelDAO.findAll();
    }
}
