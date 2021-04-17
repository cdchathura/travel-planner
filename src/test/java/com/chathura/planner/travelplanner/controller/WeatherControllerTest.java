package com.chathura.planner.travelplanner.controller;

import com.chathura.planner.travelplanner.client.WeatherAPIClient;
import com.chathura.planner.travelplanner.exception.NotFoundException;
import com.chathura.planner.travelplanner.model.WeatherList;
import com.chathura.planner.travelplanner.model.response.WeatherDetails;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherControllerTest {
    private final Gson gson = new Gson();
    @Value("classpath:sample_list_response.json")
    Resource sampleFile;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private WeatherAPIClient weatherAPIClient;

    @Test
    public void test_get_weather_by_country_code_success() throws Exception {
        String resp = Files.readString(sampleFile.getFile().toPath());
        WeatherDetails weatherDetails = gson.fromJson(resp, WeatherDetails.class);
        Mockito.when(weatherAPIClient.doGet(anyString(), any(List.class))).thenReturn(weatherDetails);
        WeatherList weatherList = this.restTemplate.getForObject("http://localhost:" + port + "/weather?city=US",
                WeatherList.class);
        assertNotNull(weatherList);
        assertEquals("US", weatherList.getCountryCode());
        assertEquals("Memphis", weatherList.getCityName());
        assertFalse(weatherList.getWeather().isEmpty());
    }

    @Test
    public void test_get_weather_by_country_code_no_content() throws Exception {
        Mockito.when(weatherAPIClient.doGet(anyString(), any(List.class))).thenThrow(new NotFoundException(new RuntimeException()));
        ResponseEntity<WeatherList> weatherList = this.restTemplate.getForEntity("http://localhost:" + port + "/weather?city=TEST",
                WeatherList.class);
        assertEquals(weatherList.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
