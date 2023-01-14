package com.mikhaildolgopolov.spring.controllers;

import com.mikhaildolgopolov.csv.MyCsvWriter;
import com.mikhaildolgopolov.csv.MyCsvReader;
import com.mikhaildolgopolov.spring.database.DatabaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/file_upload/")
public class FileUploadController {
    @Value("${upload.path}")
    private String uploadPath;

    private MyCsvWriter generator;

    @GetMapping("/")
    public String VanilaPage(){

        return "DataManipulation";
    }
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String upload(@RequestPart("file") MultipartFile multipartFile,
                         @RequestParam (name = "table") String option,
                         RedirectAttributes attr)
    {
        attr.addFlashAttribute("Inserted", false);
        try{
            if (multipartFile.isEmpty()) throw new FileNotFoundException("File not provided");
            List<String[]> lines = MyCsvReader.ReadFile(multipartFile.getInputStream());
            try {
                DatabaseService.instance.Insert(lines, option);
            } catch (SQLException se){
                attr.addFlashAttribute("Inserted", true);
                attr.addFlashAttribute("Message2",
                        "SQL error inserting data: "+se.getMessage());
            }
        }
        catch (Exception io){
            attr.addFlashAttribute("Inserted", true);
            attr.addFlashAttribute("Message2",
                    "Error reading the file: "+io.getMessage());
        }
        return "redirect:/file_upload/";
    }
    @RequestMapping(value="/generate", method=RequestMethod.POST)
    public String generate(@RequestParam("rs") String rs,
                           @RequestParam(name = "datatable") String dataType,
                           @RequestParam("fn") String filename, RedirectAttributes attr){
        attr.addFlashAttribute("Generated", false);
        if(filename.isBlank()){
            attr.addFlashAttribute("Generated", true);
            attr.addFlashAttribute("Message", "Empty file name");
            return "redirect:/file_upload/";
        }
        if(generator == null) generator = new MyCsvWriter(uploadPath);
        System.out.println(uploadPath);
        int rows;
        try {
            rows = Integer.parseInt(rs);
            if ( rows < 1) throw new RuntimeException();
        } catch (Exception e) {
            attr.addFlashAttribute("Generated", true);
            attr.addFlashAttribute("Message", "Invalid rows number");
            return "redirect:/file_upload/";
        }

        try {
            switch (dataType) {
                case "customers" -> generator.GenerateCustomers(rows, filename);
                case "mcc_codes" -> generator.GenerateCodes(rows, filename);
                case "tran_types" -> generator.GenerateTranTypes(rows, filename);
                case "transactions" -> generator.GenerateTransactions(rows, filename);
            }
            attr.addFlashAttribute("Generated", true);
            attr.addFlashAttribute("Message", "Generated "+rs+" rows in "+filename);
        } catch (IOException e){
            attr.addFlashAttribute("Generated", true);
            attr.addFlashAttribute("Message", "Error generating: "+e.getMessage());
        }

        return "redirect:/file_upload/";
    }
    @GetMapping(path="/addfile")
    public String waitCustomers(Map<String,Object> model){
        model.put("Generated", false);
        model.put("Message", "");
        generator = new MyCsvWriter(uploadPath);
        return "DataManipulation";
    }
}
