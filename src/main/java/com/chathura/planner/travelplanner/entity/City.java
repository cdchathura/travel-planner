package com.chathura.planner.travelplanner.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "city")
@Data
public class City implements Serializable {
    private static final long serialVersionUID = 325405287260623987L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cities")
    private Set<Itinerary> itineraries;
}
