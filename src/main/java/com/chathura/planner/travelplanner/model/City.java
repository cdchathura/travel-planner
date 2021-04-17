package com.chathura.planner.travelplanner.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class City implements Serializable {
    private static final long serialVersionUID = 5799337695802755414L;
    private Integer id;
    private String name;
    private String country;
}
