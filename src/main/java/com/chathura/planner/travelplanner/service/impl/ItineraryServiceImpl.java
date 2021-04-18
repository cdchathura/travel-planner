package com.chathura.planner.travelplanner.service.impl;

import com.chathura.planner.travelplanner.entity.City;
import com.chathura.planner.travelplanner.exception.NotFoundException;
import com.chathura.planner.travelplanner.mapper.ItineraryMapper;
import com.chathura.planner.travelplanner.model.Itinerary;
import com.chathura.planner.travelplanner.repository.CityRepository;
import com.chathura.planner.travelplanner.repository.ItineraryRepository;
import com.chathura.planner.travelplanner.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItineraryServiceImpl implements ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final ItineraryMapper itineraryMapper;
    private final CityRepository cityRepository;
    @Override
    @Transactional
    public Itinerary saveItinerary(Itinerary itinerary) {
        com.chathura.planner.travelplanner.entity.Itinerary itineraryEn  = itineraryMapper.apiToEntity(itinerary);
        final com.chathura.planner.travelplanner.entity.Itinerary  itineraryRes = itineraryRepository.save(itineraryEn);

        itinerary.getCities().stream().parallel().forEach(entry -> {
            Objects.requireNonNull(entry.getName(), "City name cannot be null");
            City city = cityRepository.findByName(entry.getName().trim()).orElseThrow(NotFoundException::new);
            city.getItineraries().add(itineraryRes);
            cityRepository.save(city);
        });

        log.info("Itinerary saved successfully {} ", itinerary.getId());
        return itineraryMapper.entityToApi(itineraryEn);
    }
    @Override
    public Itinerary getItineraryByName(String name) {
        log.info("Searching itinerary by {} ", name);
        return itineraryMapper.entityToApi(itineraryRepository.findByName(name).orElseThrow(NotFoundException::new));
    }
}
