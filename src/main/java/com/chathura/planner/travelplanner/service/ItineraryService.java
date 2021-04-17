package com.chathura.planner.travelplanner.service;

import com.chathura.planner.travelplanner.model.Itinerary;

public interface ItineraryService {
    Itinerary saveItinerary(Itinerary itinerary);

    Itinerary getItineraryByName(String name);
}
