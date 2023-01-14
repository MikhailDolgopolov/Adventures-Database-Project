package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.spring.database.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping("/stats/")
public class StatisticsController {
    private int grouping=5;

    @GetMapping(path="/")
    public String mainStats(Map<String,Object> model){
        return PutDataSafely(model);
    }

    private String PutDataSafely(Map<String, Object> model) {
        model.put("num", grouping);
        try{
            model.put("Error", false);
            model.put("Groupings", DatabaseService.instance.getDispersion(grouping));
        } catch (SQLException se){
            model.put("Error", true);
            model.put("Message", "SQL: "+se.getMessage());
        }
        return "Statistics";
    }

    @GetMapping(path="/{in}")
    public String MainStats(@PathVariable() int in, Map<String,Object> model){
        grouping=in;
        return PutDataSafely(model);
    }

    @PostMapping(path="/number/")
    public String Stats(@RequestParam(name="in") String n, Map<String,Object> model){
        try {
            grouping=Integer.parseInt(n);
            model.put("num", grouping);
            try {
                model.put("Groupings", DatabaseService.instance.getDispersion(grouping));
            } catch (SQLException se){
                model.put("Error", true);
                model.put("Message", "SQL: "+se.getMessage());
            }
        } catch (NumberFormatException ne){
            model.put("Error", true);
            model.put("Message", "Invalid number");
            return "Statistics";
        }

        return "redirect:/stats/"+n;
    }
}
