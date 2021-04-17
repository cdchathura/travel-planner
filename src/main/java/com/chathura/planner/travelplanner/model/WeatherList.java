package com.chathura.planner.travelplanner.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WeatherList extends RepresentationModel<WeatherList> implements Serializable {
    private static final long serialVersionUID = 292225242734599143L;
    private String cityName;
    private String countryCode;
    private List<Weather> weather;
}
