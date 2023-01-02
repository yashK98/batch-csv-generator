package com.personal.csvgenerator.util;

import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class CSVGeneratorUtil {

    public static void generateFile(String fileName, ArrayList<String> headers, ArrayList<ArrayList<String>> content) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName));
        csvWriter.writeNext(headers.toArray(String[] :: new));
        for(ArrayList<String> csvContent : content){
            csvWriter.writeNext(csvContent.toArray(String[] :: new));
        }
        csvWriter.close();
    }
}
