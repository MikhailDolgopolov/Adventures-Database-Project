package com.mikhaildolgopolov.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;

import java.util.List;

public class MyCsvReader {
    public static List<String[]> ReadFile(InputStream stream) throws IOException, CsvException {
        List<String[]> r;
        try (CSVReader reader = new CSVReader(new InputStreamReader(stream))) {
            r = reader.readAll();
        }
        return r;
    }
}
