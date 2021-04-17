package com.chathura.planner.travelplanner.mapper;

import com.chathura.planner.travelplanner.entity.City;
import com.chathura.planner.travelplanner.entity.Itinerary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface CityMapper {
    com.chathura.planner.travelplanner.model.City entityToApi(City city);
}
