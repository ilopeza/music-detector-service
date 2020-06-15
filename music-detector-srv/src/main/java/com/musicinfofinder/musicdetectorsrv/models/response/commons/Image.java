package com.musicinfofinder.musicdetectorsrv.models.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * An image object to model the responses
 *
 * @see: https://developer.spotify.com/documentation/web-api/reference/object-model/#image-object
 */
@Data
public class Image {
    @JsonProperty("url")
    private String url;//The source URL of the image.

    @JsonProperty("height")
    private Integer height;    //The image height in pixels. If unknown: null or not returned.

    @JsonProperty("width")
    private Integer width; //The image width in pixels. If unknown: null or not returned.
}
