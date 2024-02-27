package com.example.CountriesClient.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Country {

    private String name;
    private String capital;
    private int population;
    private String flag;
}
