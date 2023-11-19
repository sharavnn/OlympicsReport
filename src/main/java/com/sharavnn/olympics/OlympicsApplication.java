package com.sharavnn.olympics;

import com.sharavnn.olympics.repository.OlympicsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OlympicsApplication {

    @Autowired
    OlympicsDataRepository olympicsDataRepository;

    public static void main(String[] args) {
        SpringApplication.run(OlympicsApplication.class, args);
    }

//	@Override
//	public void run(String...args)throws Exception{
//		olympicsDataRepository.save(Olympics.builder().playername("Sharavanan").country("India").oyear(2020L).sport("Cricket").medalscount(2L).build());
//		System.out.println("Insertion Completed");
//	}
}
