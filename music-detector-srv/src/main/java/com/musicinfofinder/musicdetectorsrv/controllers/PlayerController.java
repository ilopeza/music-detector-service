package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.mapper.CurrentPlayinItemMapper;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.CurrentPlayingItemDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.player.CurrentPlaying;
import com.musicinfofinder.musicdetectorsrv.services.player.IPlayerService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/player")
public class PlayerController {

    private IPlayerService playerService;

    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Get the User's Currently Playing Track
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/currently-playing")
    public CurrentPlayingItemDTO getCurrentPlaying(@PathVariable("userId") String userId) {
        log.info("Calling /{userId}/currently-playing for user {}", userId);
        val currentPlaying = playerService.getCurrentPlaying(userId);
        val currentPlayingItemDTO = CurrentPlayinItemMapper.toCurrentPlayinItemDTO(currentPlaying.getItem());
        log.info("Returning CurrentPlayingItem: {}", currentPlayingItemDTO);
        return currentPlayingItemDTO;
    }
}
