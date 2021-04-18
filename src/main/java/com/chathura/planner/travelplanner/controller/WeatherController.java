package com.chathura.planner.travelplanner.controller;

import com.chathura.planner.travelplanner.model.WeatherList;
import com.chathura.planner.travelplanner.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/weather")
    public HttpEntity<WeatherList> getWeatherDetailsByCity(@RequestParam(value = "city") String city) {
        WeatherList weatherList = weatherService.getWeatherDetailsByCity(city);
        weatherList.add(linkTo(methodOn(WeatherController.class).getWeatherDetailsByCity(city)).withSelfRel());
        if (CollectionUtils.isEmpty(weatherList.getWeather())) {
            return new ResponseEntity<>(weatherList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(weatherList, HttpStatus.OK);
    }
}
