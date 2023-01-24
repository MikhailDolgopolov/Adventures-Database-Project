package com.mikhaildolgopolov.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trip-point/")
public class TripPointController {
    @GetMapping("/")
    public String main(Model model){
        return "Point";
    }
}
