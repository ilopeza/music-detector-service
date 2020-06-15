package com.musicinfofinder.musicdetectorsrv.models.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Restriction {

    @JsonProperty("reason")
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
