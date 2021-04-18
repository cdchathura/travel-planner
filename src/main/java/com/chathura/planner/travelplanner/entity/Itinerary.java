package com.chathura.planner.travelplanner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "itinerary")
@Getter
@Setter
public class Itinerary implements Serializable {
    private static final long serialVersionUID = 3008468114336003244L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ItineraryMapping> itineraryMappings = new HashSet<>();

    @PrePersist
    public void prePersists() {
        createdTime = LocalDateTime.now();
    }
}
