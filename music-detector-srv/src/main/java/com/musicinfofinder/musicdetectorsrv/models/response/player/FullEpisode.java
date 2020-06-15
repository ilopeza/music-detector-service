package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Object to model the episode of a podcast
 *
 * @see:https://developer.spotify.com/documentation/web-api/reference/object-model/#episode-object-full
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FullEpisode extends CurrentPlayingItem {
    @JsonProperty("audio_preview_url")
    private String audioPreviewUrl; //A URL to a 30 second preview (MP3 format) of the episode. null if not available.
    @JsonProperty("description")
    private String description;    //A description of the episode.
    @JsonProperty("images")
    private List<Image> images; //The cover art for the episode in various sizes, widest first.
    @JsonProperty("language")
    private String language;
    @JsonProperty("release_date")
    private String releaseDate; //The date the episode was first released, for example "1981-12-15". Depending on the precision, it might be shown as "1981" or "1981-12".
    @JsonProperty("release_date_precision")
    private ReleaseDatePrecisionEnum releaseDatePrecision; //The precision with which release_date value is known: "year", "month", or "day".
    @JsonProperty("resume_point")
    private ResumePoint resumePoint; //The user’s most recent position in the episode. Set if the supplied access token is a user token and has the scope user-read-playback-position.
    @JsonProperty("show")
    private Show show; //The show on which the episode belongs.
}
