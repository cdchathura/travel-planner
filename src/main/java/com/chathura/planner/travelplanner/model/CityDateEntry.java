package com.chathura.planner.travelplanner.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityDateEntry implements Serializable {
    private static final long serialVersionUID = 3488840273044731581L;
    private String name;
    private String date;
}
