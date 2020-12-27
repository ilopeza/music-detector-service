package com.musicinfofinder.musicdetectorsrv.models.response.dto;

import com.musicinfofinder.musicdetectorsrv.models.response.player.ObjectTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistDTO {
    private String id; //The Spotify ID for the artist.
    private String name; //The name of the artist.
    private ObjectTypeEnum type; //The object type: "artist"
    private String uri;   //The Spotify URI for the artist.
}
