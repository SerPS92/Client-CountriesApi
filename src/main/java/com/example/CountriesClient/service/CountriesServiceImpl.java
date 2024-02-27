package com.example.CountriesClient.service;

import com.example.CountriesClient.model.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountriesServiceImpl implements ICountriesService {

    String url = "https://restcountries.com/v2/all";

    private final RestTemplate restTemplate;

    public CountriesServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Country> showCountries() {
        String result = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Country> countries = new ArrayList<>();
        ArrayNode array;
        try {
            array = (ArrayNode) objectMapper.readTree(result);
            for (Object object : array) {
                ObjectNode json = (ObjectNode) object;
                countries.add(
                        new Country(
                                json.get("name").asText(),
                                (json.has("capital") ? json.get("capital").asText(): "N/A"),
                                json.get("population").asInt(),
                                json.get("flag").asText()));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return countries;
    }

    @Override
    public List<Country> findCountry(String name) {
        return showCountries()
                .stream()
                .filter(p -> p.getName().contains(name))
                .collect(Collectors.toList());
    }
}
