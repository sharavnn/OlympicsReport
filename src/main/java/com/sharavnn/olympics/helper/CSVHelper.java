package com.sharavnn.olympics.helper;

import com.sharavnn.olympics.model.Olympics;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Olympics> csvToDB(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Olympics> olympicsData = new ArrayList<Olympics>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                olympicsData.add(Olympics.builder()
                        .playername(csvRecord.get("Name"))
                        .sport(csvRecord.get("Sport"))
                        .country(csvRecord.get("Team"))
                        .oyear(Long.parseLong(csvRecord.get("Year")))
                        .medalscount(Long.parseLong(csvRecord.get("Medal")))
                        .build());
            }
            return olympicsData;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


}
