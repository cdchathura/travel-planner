package com.chathura.planner.travelplanner.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Weather {
    private String temperature;
    private String clouds;
    private LocalDateTime date;
}
