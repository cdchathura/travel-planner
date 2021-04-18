package com.chathura.planner.travelplanner.service.impl;

import com.chathura.planner.travelplanner.entity.City;
import com.chathura.planner.travelplanner.mapper.CityMapper;
import com.chathura.planner.travelplanner.repository.CityRepository;
import com.chathura.planner.travelplanner.service.CityService;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    @Value("classpath:city_list.json")
    private Resource allCities;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final Gson gson =  new Gson();

    /**
     * this is just to demo the project
     * @throws IOException
     */
    @PostConstruct
    public void initCities() throws IOException {
        try (
                InputStream inputStream = Files.newInputStream(allCities.getFile().toPath());
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        ) {
            log.info("Inserting all cities to database ");
            reader.beginArray();
            while (reader.hasNext()) {
                City city = gson.fromJson(reader, City.class);
                cityRepository.save(city);
            }
            reader.endArray();
            log.info("Inserting all cities to database done ");
        }
    }

    @Override
    public List<com.chathura.planner.travelplanner.model.City> getAllCities(){
        return cityRepository.findAll().stream().map(cityMapper::entityToApi).collect(Collectors.toList());
    }
}
