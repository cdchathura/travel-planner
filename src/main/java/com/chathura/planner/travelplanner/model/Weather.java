package com.chathura.planner.travelplanner.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class Weather implements Serializable {
    private static final long serialVersionUID = 8133139758216133068L;
    private String temperature;
    private String clouds;
    private LocalDateTime date;
}
