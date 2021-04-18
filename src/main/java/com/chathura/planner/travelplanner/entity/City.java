package com.chathura.planner.travelplanner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "city")
@Getter
@Setter
public class City implements Serializable {
    private static final long serialVersionUID = 325405287260623987L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ItineraryMapping> itineraryMappings = new HashSet<>();
}
