package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.ExternalUrl;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Image;
import lombok.Data;

import java.util.List;

/**
 * A simplified model of a show
 *
 * @see :https://developer.spotify.com/documentation/web-api/reference/object-model/#show-object-simplified
 */
@Data
public class Show {
    @JsonProperty("available_markets")
    private List<String> availableMarkets;    //A list of the countries in which the show can be played, identified by their ISO 3166-1 alpha-2 code.
    @JsonProperty("description")
    private String description; //A description of the show.
    @JsonProperty("explicit")
    private Boolean explicit;//Whether or not the show has explicit content (true = yes it does; false = no it does not OR unknown).
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls; //Known external URLs for this show.
    @JsonProperty("href")
    private String href; //A link to the Web API endpoint providing full details of the show.
    @JsonProperty("id")
    private String id;//The Spotify ID for the show.
    @JsonProperty("images")
    private List<Image> images; //The cover art for the show in various sizes, widest first.
    @JsonProperty("is_externally_hosted")
    private Boolean externallyHosted; //True if all of the show’s episodes are hosted outside of Spotify’s CDN. This field might be null in some cases.
    @JsonProperty("languages")
    private List<String> languages; //A list of the languages used in the show, identified by their ISO 639 code.
    @JsonProperty("media_type")
    private String mediaType; //The media type of the show.
    @JsonProperty("name")
    private String name; //The name of the show.
    @JsonProperty("publisher")
    private String publisher;//The publisher of the show.
    @JsonProperty("type")
    private ObjectTypeEnum type;//The object type: “show”.
    @JsonProperty("uri")
    private String uri; //The Spotify URI for the show.
}
