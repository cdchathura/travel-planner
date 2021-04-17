package com.chathura.planner.travelplanner.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class City implements Serializable {
    private static final long serialVersionUID = 325405287260623987L;
    @ManyToMany
    @JoinTable(
            name = "city_itinerary",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    Set<Itinerary> itineraries;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
}
