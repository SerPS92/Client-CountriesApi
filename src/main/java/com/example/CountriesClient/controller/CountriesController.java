package com.example.CountriesClient.controller;

import com.example.CountriesClient.model.Country;
import com.example.CountriesClient.service.ICountriesService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountriesController {

    private final ICountriesService countriesService;

    public CountriesController(ICountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> countries(){
        return countriesService.showCountries();
    }

    @GetMapping(value = "/countries/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> countries(@PathVariable(name = "name")String name){
        return countriesService.findCountry(name);
    }
}
