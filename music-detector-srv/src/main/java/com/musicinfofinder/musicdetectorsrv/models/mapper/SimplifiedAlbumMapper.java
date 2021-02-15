package com.musicinfofinder.musicdetectorsrv.models.mapper;

import com.musicinfofinder.musicdetectorsrv.models.response.dto.AlbumDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.player.AlbumSimplified;

import java.util.stream.Collectors;

public class SimplifiedAlbumMapper {

    public SimplifiedAlbumMapper() {}

    public static AlbumDTO toAlbumDTO(AlbumSimplified album) {
        var artistDTOS = album.getArtists().stream()
                .map(SimplifiedArtistMapper::toArtistDTO)
                .collect(Collectors.toList());
        return AlbumDTO.builder()
                .id(album.getId())
                .href(album.getHref())
                .name(album.getName())
                .releaseDate(album.getReleaseDate())
                .artists(artistDTOS)
                .build();
    }
}
