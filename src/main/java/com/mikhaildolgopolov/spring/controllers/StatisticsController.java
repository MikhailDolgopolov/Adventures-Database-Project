package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping("/schemas/")
public class StatisticsController {

    @GetMapping(path="/")
    public String mainStats(Map<String,Object> model){
        return PutDataSafely(model, 5);
    }

    private String PutDataSafely(Map<String, Object> model, int grouping) {
        model.put("num", grouping);

        return "Statistics";
    }

    @GetMapping(path="/{in}")
    public String MainStats(@PathVariable() int in, Map<String,Object> model){
        return PutDataSafely(model, in);
    }

    @PostMapping(path="/number/")
    public String Stats(@RequestParam(name="in") String n, Map<String,Object> model){
        try {
            int grouping=Integer.parseInt(n);
            model.put("num", grouping);

        } catch (NumberFormatException ne){
            model.put("Error", true);
            model.put("Message", "Invalid number");
            return "Statistics";
        }

        return "redirect:/stats/"+n;
    }
}
