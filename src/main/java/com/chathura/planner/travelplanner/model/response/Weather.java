package com.chathura.planner.travelplanner.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Weather {
    private Main main;
    private Clouds clouds;
    @JsonProperty("dt_txt")
    @SerializedName(value = "dt_txt")
    private String timestamp;
}
