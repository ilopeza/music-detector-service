package com.musicinfofinder.musicdetectorsrv.models.response.dto;

import com.musicinfofinder.musicdetectorsrv.models.response.player.ObjectTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AlbumDTO {
    private List<ArtistDTO> artists; //	array of simplified artist objects	The artists of the album. Each artist object includes a link in href to more detailed information about the artist.
    private String href; //A link to the Web API endpoint providing full details of the album.
    private String id; //The Spotify ID for the album.
    private String name; //The name of the album. In case of an album takedown, the value may be an empty string.
    private String releaseDate; //The date the album was first released, for example 1981. Depending on the precision, it might be shown as 1981-12 or 1981-12-15.
    private ObjectTypeEnum type;    //The object type: “album”
    private String uri;//The Spotify URI for the album.
}
