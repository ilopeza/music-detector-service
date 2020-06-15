package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Context;

/**
 * Current playing item in Spotify.
 *
 * @see: https://developer.spotify.com/documentation/web-api/reference/player/get-the-users-currently-playing-track/
 */
public class CurrentPlaying {
    @JsonProperty("context")
    private Context context; //A Context Object. Can be null.
    @JsonProperty("timestamp")
    private Long timestamp; //Unix Millisecond Timestamp when data was fetched
    @JsonProperty("progress_ms")
    private Integer progressMs; //Progress into the currently playing track or episode. Can be null.
    @JsonProperty("is_playing")
    private Boolean playing; //If something is currently playing.
    @JsonProperty("item")
    private CurrentPlayingItem item;    //A Full Track Object or A Full Episode Object	The currently playing track or episode. Can be null.
    @JsonProperty("currently_playing_type")
    private ObjectTypeEnum type; //The object type of the currently playing item. Can be one of track, episode, ad or unknown.

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getProgressMs() {
        return progressMs;
    }

    public void setProgressMs(Integer progressMs) {
        this.progressMs = progressMs;
    }

    public Boolean getPlaying() {
        return playing;
    }

    public void setPlaying(Boolean playing) {
        this.playing = playing;
    }

    public CurrentPlayingItem getItem() {
        return item;
    }

    public void setItem(CurrentPlayingItem item) {
        this.item = item;
    }

    public ObjectTypeEnum getType() {
        return type;
    }

    public void setType(ObjectTypeEnum type) {
        this.type = type;
    }
}
