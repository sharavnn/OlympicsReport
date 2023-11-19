package com.sharavnn.olympics.service;

import com.sharavnn.olympics.helper.CSVHelper;
import com.sharavnn.olympics.model.Olympics;
import com.sharavnn.olympics.repository.OlympicsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    OlympicsDataRepository olympicsDataRepository;

    public void save(MultipartFile file) {
        try {
            List<Olympics> olympicsList = CSVHelper.csvToDB(file.getInputStream());
            olympicsDataRepository.saveAll(olympicsList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
