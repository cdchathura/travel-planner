package com.chathura.planner.travelplanner.controller;

import com.chathura.planner.travelplanner.model.CityDateEntry;
import com.chathura.planner.travelplanner.model.Itinerary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItineraryControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_insert_itinerary_success() throws Exception {
        Itinerary itinerary = new Itinerary();
        itinerary.setName("myItinerary");
        Set<CityDateEntry> entries = new HashSet<>();
        CityDateEntry cityDateEntry = new CityDateEntry();
        cityDateEntry.setDate("04/25/2020");
        cityDateEntry.setName("Rudum");
        entries.add(cityDateEntry);
        itinerary.setCityDateEntries(entries);
        Itinerary weatherListResponseEntity = this.restTemplate.postForObject("http://localhost:" + port + "/itinerary",
                itinerary, Itinerary.class);
        assertNotNull(weatherListResponseEntity);
        assertNotNull(weatherListResponseEntity.getId());

    }

    @Test
    public void test_insert_itinerary_missing_city_name() throws Exception {
        Itinerary itinerary = new Itinerary();
        itinerary.setName("myItinerary");
        Set<CityDateEntry> entries = new HashSet<>();
        CityDateEntry cityDateEntry = new CityDateEntry();
        cityDateEntry.setDate("04/25/2020");
        entries.add(cityDateEntry);
        itinerary.setCityDateEntries(entries);
        ResponseEntity<Itinerary> weatherListResponseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/itinerary",
                itinerary, Itinerary.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, weatherListResponseEntity.getStatusCode());
    }

}
