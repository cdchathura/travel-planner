package com.chathura.planner.travelplanner.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WeatherList extends RepresentationModel<WeatherList> {
    private String cityName;
    private String countryCode;
    private List<Weather> weather;
}
