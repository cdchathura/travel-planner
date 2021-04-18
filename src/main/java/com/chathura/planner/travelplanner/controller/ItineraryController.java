package com.chathura.planner.travelplanner.controller;

import com.chathura.planner.travelplanner.model.Itinerary;
import com.chathura.planner.travelplanner.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping("/itinerary")
    public HttpEntity<Itinerary> saveItinerary(@RequestBody Itinerary itinerary) {
        Itinerary itineraryResp = itineraryService.saveItinerary(itinerary);
        return new ResponseEntity<>(itineraryResp, HttpStatus.CREATED);
    }

    @GetMapping("/itinerary")
    public HttpEntity<Itinerary> getItineraryByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(itineraryService.getItineraryByName(name), HttpStatus.OK);
    }
}
