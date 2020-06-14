package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.ExternalUrl;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Restriction;

import java.util.List;

/**
 * To model the full track object from spotify-api
 *
 * @see : https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full
 */
public class FullTrack extends CurrentPlayingItem {
    @JsonProperty("album")
    private AlbumSimplified album;    //The album on which the track appears. The album object includes a link in href to full information about the album.
    @JsonProperty("artists")
    private List<ArtistSimplified> artists;    //The artists who performed the track. Each artist object includes a link in href to more detailed information about the artist.
    @JsonProperty("available_markets")
    private List<String> availableMarkets; //A list of the countries in which the track can be played, identified by their ISO 3166-1 alpha-2 code.
    @JsonProperty("disc_number")
    private int discNumber; //The disc number (usually 1 unless the album consists of more than one disc).
    @JsonProperty("explicit")
    private boolean explicit; //Whether or not the track has explicit lyrics ( true = yes it does; false = no it does not OR unknown).
    @JsonProperty("external_ids")
    private ExternalUrl externalIds; //Known external IDs for the track.
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls; //Known external URLs for this track.

    @JsonProperty("linked_from")
    private TrackLink linkedFrom;    //Part of the response when Track Relinking is applied, and the requested track has been replaced with different track. The track in the linked_from object contains information about the originally requested track.
    @JsonProperty("restrictions")
    private Restriction restrictions;//Part of the response when Track Relinking is applied, the original track is not available in the given market, and Spotify did not have any tracks to relink it with. The track response will still contain metadata for the original track, and a restrictions object containing the reason why the track is not available: "restrictions" : {"reason" : "market"}

    @JsonProperty("popularity")
    private Integer popularity; //The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.The popularity of a track is a value between 0 and 100, with 100 being the most popular. The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how recent those plays are.
    @JsonProperty("preview_url")
    private String previewUrl;//A link to a 30 second preview (MP3 format) of the track. Can be null
    @JsonProperty("track_number")
    private Integer trackNumber;//The number of the track. If an album has several discs, the track number is the number on the specified disc.

    @JsonProperty("is_local")
    private Boolean local; //Whether or not the track is from a local file.

    public AlbumSimplified getAlbum() {
        return album;
    }

    public void setAlbum(AlbumSimplified album) {
        this.album = album;
    }

    public List<ArtistSimplified> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistSimplified> artists) {
        this.artists = artists;
    }

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public ExternalUrl getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalUrl externalIds) {
        this.externalIds = externalIds;
    }

    public ExternalUrl getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrl externalUrls) {
        this.externalUrls = externalUrls;
    }

    public TrackLink getLinkedFrom() {
        return linkedFrom;
    }

    public void setLinkedFrom(TrackLink linkedFrom) {
        this.linkedFrom = linkedFrom;
    }

    public Restriction getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Restriction restrictions) {
        this.restrictions = restrictions;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Boolean getLocal() {
        return local;
    }

    public void setLocal(Boolean local) {
        this.local = local;
    }
}
