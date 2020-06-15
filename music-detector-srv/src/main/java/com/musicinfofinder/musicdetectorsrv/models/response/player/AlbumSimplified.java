package com.musicinfofinder.musicdetectorsrv.models.response.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.ExternalUrl;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Image;
import lombok.Data;

import java.util.List;

/**
 * To model a simplified view of an album
 *
 * @see //developer.spotify.com/documentation/web-api/reference/object-model/#album-object-simplified
 */
@Data
public class AlbumSimplified {
    @JsonProperty("album_group")
    private String albumGroup;// The field is present when getting an artist’s albums. Possible values are “album”, “single”, “compilation”, “appears_on”. Compare to album_type this field represents relationship between the artist and the album.
    @JsonProperty("album_type")
    private AlbumTypeEnum albumType; //The type of the album: one of “album”, “single”, or “compilation”.
    @JsonProperty("artists")
    private List<ArtistSimplified> artists; //	array of simplified artist objects	The artists of the album. Each artist object includes a link in href to more detailed information about the artist.
    @JsonProperty("available_markets")
    private List<String> availableMarkets;// The markets in which the album is available: ISO 3166-1 alpha-2 country codes. Note that an album is considered available in a market when at least 1 of its tracks is available in that market.
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls;    //external URL object	Known external URLs for this album.
    @JsonProperty("href")
    private String href; //A link to the Web API endpoint providing full details of the album.
    @JsonProperty("id")
    private String id; //The Spotify ID for the album.
    @JsonProperty("images")
    List<Image> images; //The cover art for the album in various sizes, widest first.
    @JsonProperty("name")
    private String name; //The name of the album. In case of an album takedown, the value may be an empty string.
    @JsonProperty("release_date")
    private String releaseDate; //The date the album was first released, for example 1981. Depending on the precision, it might be shown as 1981-12 or 1981-12-15.
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;    //The precision with which release_date value is known: year , month , or day.
    @JsonProperty("type")
    private ObjectTypeEnum type;    //The object type: “album”
    @JsonProperty("uri")
    private String uri;//The Spotify URI for the album.
}
