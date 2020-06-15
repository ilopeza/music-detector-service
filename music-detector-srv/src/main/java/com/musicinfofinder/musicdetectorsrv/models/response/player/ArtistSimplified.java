package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.ExternalUrl;

/**
 * To model a simplified artist
 *
 * @see:https://developer.spotify.com/documentation/web-api/reference/object-model/#artist-object-simplified
 */
public class ArtistSimplified {
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls;    //an external URL object	Known external URLs for this artist.
    @JsonProperty("href")
    private String href; //A link to the Web API endpoint providing full details of the artist.
    @JsonProperty("id")
    private String id; //The Spotify ID for the artist.
    @JsonProperty("name")
    private String name; //The name of the artist.
    @JsonProperty("type")
    private ObjectTypeEnum type; //The object type: "artist"
    @JsonProperty("uri")
    private String uri;   //The Spotify URI for the artist.
}
