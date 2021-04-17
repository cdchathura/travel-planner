package com.chathura.planner.travelplanner.service.impl;

import com.chathura.planner.travelplanner.client.WeatherAPIClient;
import com.chathura.planner.travelplanner.model.Weather;
import com.chathura.planner.travelplanner.model.WeatherList;
import com.chathura.planner.travelplanner.model.response.WeatherDetails;
import com.chathura.planner.travelplanner.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final static String GET_WEATHER_URL = "/data/2.5/forecast?q=%s&appid=%s&units=metric";

    private final WeatherAPIClient weatherAPIClient;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public WeatherList getWeatherDetailsByCity(String city) {
        List<Object> parameters = new ArrayList<>(2);
        parameters.add(city);
        log.info("Calling weather API {} ", city);
        WeatherDetails weatherDetails = weatherAPIClient.doGet(GET_WEATHER_URL, parameters);
        return WeatherList.builder()
                .cityName(weatherDetails.getCity().getName())
                .countryCode(weatherDetails.getCity().getCountry())
                .weather(weatherDetails.getList().stream().map(weather -> Weather.builder()
                        .temperature(weather.getMain().getTemp())
                        .clouds(weather.getClouds().getAll())
                        .date(LocalDateTime.parse(weather.getTimestamp(), dateTimeFormatter))
                        .build()).filter(weather -> weather.getDate().getHour() >= 12 && weather.getDate().getHour() <= 18).collect(Collectors.toList()))
                .build();
    }
}
