package com.chathura.planner.travelplanner.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "itinerary")
@Data
public class Itinerary implements Serializable {
    private static final long serialVersionUID = 3008468114336003244L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    private LocalDateTime createdTime;

    @ManyToMany( fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
    @JoinTable(name = "city_itinerary",
            joinColumns = @JoinColumn(name = "itinerary_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id")
    )
    private Set<City> cities;

    @PrePersist
    public void prePersists() {
        createdTime = LocalDateTime.now();
    }
}
