package com.chathura.planner.travelplanner.service;

import com.chathura.planner.travelplanner.model.WeatherList;


public interface WeatherService {
    WeatherList getWeatherDetailsByCity(String city);
}
