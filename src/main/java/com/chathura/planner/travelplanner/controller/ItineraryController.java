package com.chathura.planner.travelplanner.controller;

import com.chathura.planner.travelplanner.model.Itinerary;
import com.chathura.planner.travelplanner.service.ItineraryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@Validated
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping("/itinerary")
    @ApiOperation(value = "Create itinerary in database",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    public HttpEntity<Itinerary> saveItinerary(@Valid @RequestBody Itinerary itinerary) {
        Itinerary itineraryResp = itineraryService.saveItinerary(itinerary);
        return new ResponseEntity<>(itineraryResp, HttpStatus.CREATED);
    }

    @GetMapping("/itinerary")
    @ApiOperation(value = "Search itinerary by name",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    public HttpEntity<Itinerary> getItineraryByName(@Valid @RequestParam("name") String name) {
        return new ResponseEntity<>(itineraryService.getItineraryByName(name), HttpStatus.OK);
    }
}
