package com.chathura.planner.travelplanner.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
public class Itinerary implements Serializable {
    private static final long serialVersionUID = 2392746996978995985L;
    private Integer id;
    @NotEmpty(message = "Itinerary must have a value")
    private String name;
    @NotNull
    private Set<CityDateEntry> cityDateEntries;
}
