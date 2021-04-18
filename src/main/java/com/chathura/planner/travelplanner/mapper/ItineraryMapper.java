package com.chathura.planner.travelplanner.mapper;

import com.chathura.planner.travelplanner.entity.Itinerary;
import com.chathura.planner.travelplanner.entity.ItineraryMapping;
import com.chathura.planner.travelplanner.model.CityDateEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface ItineraryMapper {

    @Mapping(ignore = true, target = "itineraryMappings")
    Itinerary apiToEntity(com.chathura.planner.travelplanner.model.Itinerary itinerary);

    @Mapping(source = "itineraryMappings", target = "cityDateEntries")
    com.chathura.planner.travelplanner.model.Itinerary entityToApi(Itinerary itinerary);

    @Mapping(source = "date", target = "date")
    @Mapping(source = "city.name", target = "name")
    CityDateEntry itineraryMappingToCityDateEntry(ItineraryMapping itineraryMapping);
}
