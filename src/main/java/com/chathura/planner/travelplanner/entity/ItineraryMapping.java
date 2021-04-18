package com.chathura.planner.travelplanner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "itinerary_mapping")
@Getter
@Setter
public class ItineraryMapping implements Serializable {
    private static final long serialVersionUID = 5050177604492679221L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    @ManyToOne
    @JoinColumn(name = "itinerary_id", nullable = false)
    private Itinerary itinerary;

    @Column(nullable = false)
    @NotNull(message = "Date cannot be null")
    private LocalDate date;

}
