package com.sharavnn.olympics.service;

import com.sharavnn.olympics.model.Olympics;
import com.sharavnn.olympics.model.OlympicsDTO;
import com.sharavnn.olympics.repository.OlympicsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OlympicsReportService {

    @Autowired
    OlympicsDataRepository olympicsDataRepository;

    public List<OlympicsDTO> getScorecardByYearAndSport(long year, String Sport) {
        return olympicsDataRepository.findByOyearAndSport(year, Sport).stream().map(this::convertToDTO).sorted(Comparator.comparingLong(OlympicsDTO::getMedalscount).reversed()).toList();
    }

    public List<OlympicsDTO> getScorecardByYear(long year) {
        return aggregateData(olympicsDataRepository.findByOyear(year));
    }

    private List<OlympicsDTO> aggregateData(List<Olympics> dataIn) {
        List<OlympicsDTO> dataOut = new ArrayList<>();
        dataIn.stream()
                .collect(Collectors.groupingBy(Olympics::getCountry, Collectors.summingLong(Olympics::getMedalscount)))
                .forEach((key, value) -> dataOut.add(OlympicsDTO.builder().country(key).medalscount(value).build()));

        return dataOut.stream().sorted(Comparator.comparingLong(OlympicsDTO::getMedalscount).reversed()).collect(Collectors.toList());
    }

    private OlympicsDTO convertToDTO(Olympics olympics) {
        return OlympicsDTO.builder()
                .playername(olympics.getPlayername())
                .country(olympics.getCountry())
                .medalscount(olympics.getMedalscount())
                .build();
    }
}
