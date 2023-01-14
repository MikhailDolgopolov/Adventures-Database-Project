package com.mikhaildolgopolov.csv;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class MyCsvWriter {

    public static String uploadPath;
    public final HashSet<Integer> customers;
    public final HashSet<Integer> mcc_codes;

    public final HashSet<Integer> mcc_types;

    public MyCsvWriter(){
        customers = new HashSet<>();
        mcc_codes = new HashSet<>();
        mcc_types = new HashSet<>();
    }
    public MyCsvWriter(String path){
        this();
        uploadPath = path;

    }
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
    public void GenerateCustomers(int n, String fileName) throws IOException{
        fileName+=".csv";
        String path = uploadPath+fileName;
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"customer_id", "gender"});
        Random rng = new Random();
        for (int i = 1; i<=n;i++){
            customers.add(i);
        }
        for (int c : customers){
            data.add(new String[]{Integer.toString(c), String.valueOf(rng.nextInt(2) )});
        }
        System.out.println(path);
        File f = new File(path);
        WriteLineByLine(data, f);

    }
    public void GenerateCodes(int n, String fileName) throws IOException{

        fileName+=".csv";
        String path = uploadPath+fileName;
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"mcc_code", "mcc_description"});
        for (int i = 1; i<=n;i++){
            mcc_codes.add(i);
        }
        for (int c : mcc_codes){
            data.add(new String[]{Integer.toString(c), "'Description of code "+ c +"'"});       }
        File f = new File(path);
        WriteLineByLine(data, f);
    }
    public void GenerateTranTypes(int n, String fileName) throws IOException{
        fileName+=".csv";
        String path = uploadPath+fileName;
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"tr_type", "tr_description"});
        for (int i = 1; i<=n;i++){
            mcc_types.add(i);
        }
        for (int c : mcc_types){
            data.add(new String[]{Integer.toString(c), "'Description of a transaction type "+ c +"'"});
        }
        File f = new File(path);
        WriteLineByLine(data, f);

    }
    public void GenerateTransactions(int n, String fileName) throws IOException{
        fileName+=".csv";
        String path = uploadPath+fileName;
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"customer_id", "tr_datetime", "mcc_code", "tr_type","amount", "term_id"});
        Random rng = new Random();

        for (int i = 0; i< n;i++){
            String dt = rng.nextInt(300) +"&"+
                    rng.nextInt(11, 24) +":"+
                    rng.nextInt(60) +":"+
                    rng.nextInt(60);
            int c = rng.nextInt(100)+1;
            int mc = rng.nextInt(100)+1;
            int tt = rng.nextInt(100)+1;
            if(customers.size()>1) c=rng.nextInt(customers.size())+1;
            if(mcc_codes.size()>1) mc=rng.nextInt(mcc_codes.size())+1;
            if(mcc_types.size()>1) tt= rng.nextInt(mcc_types.size())+1;
            int amount = rng.nextInt( 5000)-2500;
            int terminal = rng.nextInt(65, 9999);
            data.add(new String[]{Integer.toString(c), dt, Integer.toString(mc),
            Integer.toString(tt), Integer.toString(amount), Integer.toString(terminal)});
        }
        File f = new File(path);
        WriteLineByLine(data, f);

    }
}
