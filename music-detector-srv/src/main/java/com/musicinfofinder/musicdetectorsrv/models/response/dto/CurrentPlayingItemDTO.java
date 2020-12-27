package com.musicinfofinder.musicdetectorsrv.models.response.dto;

import com.musicinfofinder.musicdetectorsrv.models.response.player.ObjectTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CurrentPlayingItemDTO {
    private String id; //The Spotify ID for the episode.
    private String name; //The name of the episode.
    private ObjectTypeEnum type;//The object type: “track”.
    private String href; //A link to the Web API endpoint providing full details of the episode.
    private AlbumDTO album;    //The album on which the track appears. The album object includes a link in href to full information about the album.
    private List<ArtistDTO> artists;

}
