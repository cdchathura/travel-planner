package com.chathura.planner.travelplanner.repository;

import com.chathura.planner.travelplanner.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);

}
