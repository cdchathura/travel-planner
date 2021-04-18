package com.chathura.planner.travelplanner.mapper;

import com.chathura.planner.travelplanner.entity.City;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CityMapper {
    com.chathura.planner.travelplanner.model.City entityToApi(City city);
}
