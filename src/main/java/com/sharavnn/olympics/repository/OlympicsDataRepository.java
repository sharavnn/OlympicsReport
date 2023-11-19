package com.sharavnn.olympics.repository;

import com.sharavnn.olympics.model.Olympics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OlympicsDataRepository extends JpaRepository<Olympics, Integer> {

    @Query(value = "SELECT * FROM OLYMPICS WHERE MEDALSCOUNT >=1 AND OYEAR = ?1 AND SPORT = ?2 ORDER BY MEDALSCOUNT DESC", nativeQuery = true)
    List<Olympics> findByOyearAndSport(long year, String sports);

//    @Query(value = "SELECT country, sum(medalscount) as medalscount FROM OLYMPICS WHERE MEDALSCOUNT >=1 AND OYEAR = ?1 group by country order by medalscount desc", nativeQuery = true)
//    List<Olympics> findByOyear(long year);

    @Query(value = "SELECT * FROM OLYMPICS WHERE MEDALSCOUNT >=1 AND OYEAR = ?1", nativeQuery = true)
    List<Olympics> findByOyear(long year);

}
