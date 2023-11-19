package com.sharavnn.olympics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Olympics {

    @Id
    @GeneratedValue
    private Long id;
    private String playername;
    private String sex;
    private String age;
    private String country;
    private Long oyear;
    private String sport;
    private Long medalscount;
}
