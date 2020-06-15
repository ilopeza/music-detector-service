package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResumePoint {
    @JsonProperty("fully_played")
    private Boolean fullyPlayed; //Whether or not the episode has been fully played by the user.
    @JsonProperty("resume_position_ms")
    private Integer resumePositionMs; //The user’s most recent position in the episode in milliseconds.

    public Boolean getFullyPlayed() {
        return fullyPlayed;
    }

    public void setFullyPlayed(Boolean fullyPlayed) {
        this.fullyPlayed = fullyPlayed;
    }

    public Integer getResumePositionMs() {
        return resumePositionMs;
    }

    public void setResumePositionMs(Integer resumePositionMs) {
        this.resumePositionMs = resumePositionMs;
    }
}
