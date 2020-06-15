package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Context;
import lombok.Data;

/**
 * Current playing item in Spotify.
 *
 * @see: https://developer.spotify.com/documentation/web-api/reference/player/get-the-users-currently-playing-track/
 */
@Data
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
}
