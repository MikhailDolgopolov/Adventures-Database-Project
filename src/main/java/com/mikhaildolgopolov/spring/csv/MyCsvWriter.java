package com.mikhaildolgopolov.spring.csv;

import com.opencsv.CSVWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.lang.String;

@Component
public class MyCsvWriter {
    public String uploadPath;

    public void WriteLineByLine(List<String[]> lines, File file) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END)){
            System.out.println(writer.toString());
            for (String[] line : lines){
                writer.writeNext(line);
            }
        }
    }
}
