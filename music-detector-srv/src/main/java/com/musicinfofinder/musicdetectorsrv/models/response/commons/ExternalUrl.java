package com.musicinfofinder.musicdetectorsrv.models.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalUrl {
    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;
}
