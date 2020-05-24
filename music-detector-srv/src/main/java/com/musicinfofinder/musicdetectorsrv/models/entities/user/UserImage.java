package com.musicinfofinder.musicdetectorsrv.models.entities.user;

/**
 * User image response object.
 * https://developer.spotify.com/documentation/web-api/reference/object-model/#image-object
 */
public class UserImage {
    private int height;
    private int width;
    private String url;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
