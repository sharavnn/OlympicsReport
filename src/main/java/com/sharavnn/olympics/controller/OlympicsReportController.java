package com.sharavnn.olympics.controller;

import com.sharavnn.olympics.helper.CSVHelper;
import com.sharavnn.olympics.model.OlympicsDTO;
import com.sharavnn.olympics.service.CSVService;
import com.sharavnn.olympics.service.OlympicsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class OlympicsReportController {

    @Autowired
    CSVService csvService;

    @Autowired
    OlympicsReportService olympicsReportService;

    @PostMapping("/addOlympicsInfo")
    public ResponseEntity uploadData(@RequestParam("file") MultipartFile file) {

        if (CSVHelper.hasCSVFormat(file)) {
            csvService.save(file);
            return ResponseEntity.status(HttpStatus.OK).body("Uploaded Successfully");
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file");
    }

    @GetMapping("/scorecard")
    public List<OlympicsDTO> getScorecardByYear(long year, @RequestParam(value = "sport", required = false) String sport) {
        if (sport == null) {
            return olympicsReportService.getScorecardByYear(year);
        } else {
            return olympicsReportService.getScorecardByYearAndSport(year, sport);
        }
    }
}
