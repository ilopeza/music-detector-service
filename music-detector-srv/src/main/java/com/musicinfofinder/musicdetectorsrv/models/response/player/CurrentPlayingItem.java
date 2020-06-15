package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.ExternalUrl;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FullTrack.class, name = "track"),
        @JsonSubTypes.Type(value = FullEpisode.class, name = "episode")
})
@Data
public abstract class CurrentPlayingItem {
    @JsonProperty("id")
    private String id; //The Spotify ID for the episode.
    @JsonProperty("name")
    private String name; //The name of the episode.
    @JsonProperty("type")
    private ObjectTypeEnum type;//The object type: “track”.
    @JsonProperty("href")
    private String href; //A link to the Web API endpoint providing full details of the episode.
    @JsonProperty("is_externally_hosted")
    private Boolean externallyHosted; //True if the episode is hosted outside of Spotify’s CDN.
    @JsonProperty("is_playable")
    private Boolean playable; //True if the episode is playable in the given market. Otherwise false.
    @JsonProperty("uri")
    private String uri;//The Spotify URI for the track.
    @JsonProperty("duration_ms")
    private Integer durationMs; //The episode length in milliseconds.
    @JsonProperty("explicit")
    private Boolean explicit;//Whether or not the episode has explicit content (true = yes it does; false = no it does not OR unknown).
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls; //External URLs for this episode.
}
