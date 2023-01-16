package com.mikhaildolgopolov.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/home/")
public class MainController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("Title", "Домашняя страница");
        return "Home";
    }

}
