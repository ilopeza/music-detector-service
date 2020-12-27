package com.musicinfofinder.musicdetectorsrv.models.mapper;

import com.musicinfofinder.musicdetectorsrv.models.response.dto.ArtistDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.player.ArtistSimplified;

public class SimplifiedArtistMapper {

    public static ArtistDTO toArtistDTO(ArtistSimplified artist) {
        return ArtistDTO.builder()
                .id(artist.getId())
                .name(artist.getName())
                .type(artist.getType())
                .uri(artist.getUri())
                .build();
    }
}
