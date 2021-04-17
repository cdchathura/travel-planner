package com.chathura.planner.travelplanner.repository;

import com.chathura.planner.travelplanner.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
