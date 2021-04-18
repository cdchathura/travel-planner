package com.chathura.planner.travelplanner.controller;

import com.chathura.planner.travelplanner.model.WeatherList;
import com.chathura.planner.travelplanner.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
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
    @ApiOperation(value = "Will return weather entries for a given country withing UTC 12 - 18 pm",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @ApiResponses(
            {
                    @ApiResponse(code = 204, message = "No content found"),
                    @ApiResponse(code = 200, message = "Successful list of entries"),
                    @ApiResponse(code = 500, message = "internal error"),
                    @ApiResponse(code = 503, message = "Error calling weather service API")
            }
    )
    public HttpEntity<WeatherList> getWeatherDetailsByCity(@RequestParam(value = "city") String city) {
        WeatherList weatherList = weatherService.getWeatherDetailsByCity(city);
        weatherList.add(linkTo(methodOn(WeatherController.class).getWeatherDetailsByCity(city)).withSelfRel());
        if (CollectionUtils.isEmpty(weatherList.getWeather())) {
            return new ResponseEntity<>(weatherList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(weatherList, HttpStatus.OK);
    }
}
