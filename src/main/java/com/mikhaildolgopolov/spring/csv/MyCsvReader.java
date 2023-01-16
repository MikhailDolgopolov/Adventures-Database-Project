package com.mikhaildolgopolov.spring.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.*;

import java.util.List;


@Component
public class MyCsvReader {
    public List<String[]> ReadFile(InputStream stream) throws IOException, CsvException {
        List<String[]> r;
        try (CSVReader reader = new CSVReader(new InputStreamReader(stream))) {
            r = reader.readAll();
        }
        return r;
    }
}
