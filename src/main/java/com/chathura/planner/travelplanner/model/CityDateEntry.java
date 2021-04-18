package com.chathura.planner.travelplanner.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CityDateEntry implements Serializable {
    private static final long serialVersionUID = 3488840273044731581L;
    @NotEmpty(message = "City name cannot be empty")
    private String name;
    @NotEmpty(message = "Date cannot be empty")
    private String date;
}
