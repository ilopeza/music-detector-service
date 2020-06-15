package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ObjectTypeEnum {
    @JsonProperty("album")
    ALBUM("album"),
    @JsonProperty("artist")
    ARTIST("artist"),
    @JsonProperty("playlist")
    PLAYLIST("playlist"),
    @JsonProperty("episode")
    EPISODE("episode"),
    @JsonProperty("track")
    TRACK("track"),
    @JsonProperty("ad")
    AD("ad");

    String description;

    ObjectTypeEnum(String description) {
        this.description = description;
    }
}
