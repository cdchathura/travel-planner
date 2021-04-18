package com.chathura.planner.travelplanner.repository;

import com.chathura.planner.travelplanner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
    Optional<Itinerary> findByName(String name);
}
