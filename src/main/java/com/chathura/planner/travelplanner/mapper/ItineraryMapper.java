package com.chathura.planner.travelplanner.mapper;

import com.chathura.planner.travelplanner.entity.City;
import com.chathura.planner.travelplanner.entity.Itinerary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(
        componentModel = "spring"
)
public interface ItineraryMapper {
    @Mapping(ignore = true,  target = "cities")
    Itinerary apiToEntity(com.chathura.planner.travelplanner.model.Itinerary itinerary);

    @Mapping(source = "cities", target = "cities")
    com.chathura.planner.travelplanner.model.Itinerary entityToApi(Itinerary itinerary);
}
