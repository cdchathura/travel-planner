package com.chathura.planner.travelplanner.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Itinerary implements Serializable {
    private static final long serialVersionUID = 3008468114336003244L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDateTime createdTime;
    @ManyToMany(mappedBy = "itineraries")
    private Set<City> cities;

    @PrePersist
    public void prePersists() {
        createdTime = LocalDateTime.now();
    }
}
