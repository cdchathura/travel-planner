package com.chathura.planner.travelplanner.model.response;

import lombok.Data;

import java.util.List;


@Data
public class WeatherDetails {
    private City city;
    private List<Weather> list;
}
