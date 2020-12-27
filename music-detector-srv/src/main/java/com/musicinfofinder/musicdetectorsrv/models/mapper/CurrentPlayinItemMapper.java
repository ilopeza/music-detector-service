package com.musicinfofinder.musicdetectorsrv.models.mapper;

import com.musicinfofinder.musicdetectorsrv.models.response.dto.CurrentPlayingItemDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.player.CurrentPlayingItem;
import com.musicinfofinder.musicdetectorsrv.models.response.player.FullEpisode;
import com.musicinfofinder.musicdetectorsrv.models.response.player.FullTrack;
import lombok.val;

import java.util.stream.Collectors;

public class CurrentPlayinItemMapper {

    public static CurrentPlayingItemDTO toCurrentPlayinItemDTO(CurrentPlayingItem item) {
        CurrentPlayingItemDTO playingItemDTO = null;
        if (item instanceof FullTrack) {
            FullTrack track = (FullTrack) item;
            val albumDTO = SimplifiedAlbumMapper.toAlbumDTO(track.getAlbum());

            val artistDTOS = track.getArtists().stream()
                    .map(SimplifiedArtistMapper::toArtistDTO)
                    .collect(Collectors.toList());

            playingItemDTO = CurrentPlayingItemDTO.builder()
                    .album(albumDTO)
                    .artists(artistDTOS)
                    .id(track.getId())
                    .href(track.getHref())
                    .name(track.getName())
                    .type(track.getType())
                    .build();

        } else {
            FullEpisode episode = (FullEpisode) item;
            CurrentPlayingItemDTO.builder()
                    .id(episode.getId())
                    .href(episode.getHref())
                    .name(episode.getName())
                    .type(episode.getType())
                    .build();
        }
        return playingItemDTO;
    }
}
