package com.chathura.planner.travelplanner.service.impl;

import com.chathura.planner.travelplanner.exception.NotFoundException;
import com.chathura.planner.travelplanner.mapper.ItineraryMapper;
import com.chathura.planner.travelplanner.model.Itinerary;
import com.chathura.planner.travelplanner.repository.ItineraryRepository;
import com.chathura.planner.travelplanner.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItineraryServiceImpl implements ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final ItineraryMapper itineraryMapper;

    @Override
    public Itinerary saveItinerary(Itinerary itinerary) {
        com.chathura.planner.travelplanner.entity.Itinerary itineraryEn = itineraryRepository.save(itineraryMapper.apiToEntity(itinerary));
        log.info("Itinerary saved successfully {} ", itinerary.getId());
        return itineraryMapper.entityToApi(itineraryEn);
    }
    @Override
    public Itinerary getItineraryByName(String name) {
        log.info("Searching itinerary by {} ", name);
        return itineraryMapper.entityToApi(itineraryRepository.findByName(name).orElseThrow(NotFoundException::new));
    }
}
