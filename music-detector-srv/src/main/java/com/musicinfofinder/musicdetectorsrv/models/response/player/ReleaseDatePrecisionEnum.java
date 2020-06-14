package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum  ReleaseDatePrecisionEnum {
    @JsonProperty("year")
    YEAR,
    @JsonProperty("day")
    DAY,
    @JsonProperty("month")
    MONTH
}
