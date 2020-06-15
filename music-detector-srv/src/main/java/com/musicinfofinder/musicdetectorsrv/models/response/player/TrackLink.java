package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.ExternalUrl;

/**
 * @see: https://developer.spotify.com/documentation/web-api/reference/object-model/#track-link
 */
public class TrackLink {
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls; //Known external URLs for this track.
    @JsonProperty("href")
    private String href; //A link to the Web API endpoint providing full details of the track.
    @JsonProperty("id")
    private String id; //The Spotify ID for the track.
    @JsonProperty("type")
    private ObjectTypeEnum type; //The object type: “track”.
    @JsonProperty("uri")
    private String uri;//The Spotify URI for the track.

    public ExternalUrl getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrl externalUrls) {
        this.externalUrls = externalUrls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectTypeEnum getType() {
        return type;
    }

    public void setType(ObjectTypeEnum type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
