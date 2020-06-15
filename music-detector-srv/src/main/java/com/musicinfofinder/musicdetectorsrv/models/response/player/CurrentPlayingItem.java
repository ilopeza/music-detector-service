package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.ExternalUrl;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FullTrack.class, name = "track"),
        @JsonSubTypes.Type(value = FullEpisode.class, name = "episode")
})
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getExternallyHosted() {
        return externallyHosted;
    }

    public void setExternallyHosted(Boolean externallyHosted) {
        this.externallyHosted = externallyHosted;
    }

    public Boolean getPlayable() {
        return playable;
    }

    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    public ExternalUrl getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrl externalUrls) {
        this.externalUrls = externalUrls;
    }
}
