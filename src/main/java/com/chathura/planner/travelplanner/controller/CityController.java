package com.chathura.planner.travelplanner.controller;

import com.chathura.planner.travelplanner.model.City;
import com.chathura.planner.travelplanner.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class CityController {
    private final CityService cityService;

    @GetMapping("/city")
    public HttpEntity<List<City>> getAll() {
        return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
    }
}
