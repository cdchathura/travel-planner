package com.chathura.planner.travelplanner.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class Itinerary implements Serializable {
    private static final long serialVersionUID = 2392746996978995985L;
    private Integer id;
    private String name;
    private Set<CityDateEntry> cityDateEntries;
}
