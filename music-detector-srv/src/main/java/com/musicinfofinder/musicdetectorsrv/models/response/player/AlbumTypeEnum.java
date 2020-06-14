package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AlbumTypeEnum {
    @JsonProperty("album")
    ALBUM,
    @JsonProperty("single")
    SINGLE,
    @JsonProperty("compilation")
    COMPILATION
}
