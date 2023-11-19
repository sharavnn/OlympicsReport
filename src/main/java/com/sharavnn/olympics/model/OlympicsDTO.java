package com.sharavnn.olympics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OlympicsDTO {

    private String playername;
    private String country;
    private Long oyear;
    private String sport;
    private Long medalscount;

}
