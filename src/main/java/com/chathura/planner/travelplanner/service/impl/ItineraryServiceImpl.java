package com.chathura.planner.travelplanner.service.impl;

import com.chathura.planner.travelplanner.entity.City;
import com.chathura.planner.travelplanner.entity.ItineraryMapping;
import com.chathura.planner.travelplanner.exception.NotFoundException;
import com.chathura.planner.travelplanner.mapper.ItineraryMapper;
import com.chathura.planner.travelplanner.model.Itinerary;
import com.chathura.planner.travelplanner.repository.CityRepository;
import com.chathura.planner.travelplanner.repository.ItineraryMappingRepository;
import com.chathura.planner.travelplanner.repository.ItineraryRepository;
import com.chathura.planner.travelplanner.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItineraryServiceImpl implements ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final ItineraryMapper itineraryMapper;
    private final CityRepository cityRepository;
    private final ItineraryMappingRepository itineraryMappingRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    @Transactional
    public Itinerary saveItinerary(Itinerary itinerary) {
        final com.chathura.planner.travelplanner.entity.Itinerary itineraryEn = itineraryRepository.save(itineraryMapper.apiToEntity(itinerary));
        Set<ItineraryMapping> mappings = itinerary.getCityDateEntries().stream().parallel().map(entry -> {
            Objects.requireNonNull(entry.getName(), "City name cannot be null");
            City city = cityRepository.findByName(entry.getName().trim()).orElseThrow(NotFoundException::new);
            ItineraryMapping itineraryMapping = new ItineraryMapping();
            itineraryMapping.setCity(city);
            itineraryMapping.setItinerary(itineraryEn);
            itineraryMapping.setDate(LocalDate.parse(entry.getDate(), dateTimeFormatter));
            return itineraryMapping;
        }).collect(Collectors.toSet());
        itineraryMappingRepository.saveAll(mappings);
        log.info("Itinerary saved successfully {} ", itineraryEn.getId());
        return itineraryMapper.entityToApi(itineraryEn);
    }

    @Override
    public Itinerary getItineraryByName(String name) {
        log.info("Searching itinerary by {} ", name);
        com.chathura.planner.travelplanner.entity.Itinerary itineraryEn = itineraryRepository.findByName(name).orElseThrow(NotFoundException::new);
        return itineraryMapper.entityToApi(itineraryEn);
    }
}
