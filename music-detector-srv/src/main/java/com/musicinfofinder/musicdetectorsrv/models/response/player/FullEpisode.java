package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Image;

import java.util.List;

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

    public String getAudioPreviewUrl() {
        return audioPreviewUrl;
    }

    public void setAudioPreviewUrl(String audioPreviewUrl) {
        this.audioPreviewUrl = audioPreviewUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ReleaseDatePrecisionEnum getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public void setReleaseDatePrecision(ReleaseDatePrecisionEnum releaseDatePrecision) {
        this.releaseDatePrecision = releaseDatePrecision;
    }

    public ResumePoint getResumePoint() {
        return resumePoint;
    }

    public void setResumePoint(ResumePoint resumePoint) {
        this.resumePoint = resumePoint;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
