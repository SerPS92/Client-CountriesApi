package com.example.CountriesClient.service;

import com.example.CountriesClient.model.Country;

import java.util.List;

public interface ICountriesService {
    List<Country> showCountries();
    List<Country> findCountry(String name);
}
