package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.response.player.CurrentPlaying;
import com.musicinfofinder.musicdetectorsrv.services.player.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    /**
     * Get the User's Currently Playing Track
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/currently-playing")
    public CurrentPlaying getCurrentPlaying(@PathVariable("userId") String userId) {
        return playerService.getCurrentPlaying(userId);
    }
}
