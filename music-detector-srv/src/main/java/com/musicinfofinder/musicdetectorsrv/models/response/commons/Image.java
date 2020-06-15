package com.musicinfofinder.musicdetectorsrv.models.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An image object to model the responses
 * @see: https://developer.spotify.com/documentation/web-api/reference/object-model/#image-object
 */
public class Image {
    @JsonProperty("url")
    private String url;//The source URL of the image.

    @JsonProperty("height")
    private Integer height;	//The image height in pixels. If unknown: null or not returned.

    @JsonProperty("width")
    private Integer width; //The image width in pixels. If unknown: null or not returned.

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
