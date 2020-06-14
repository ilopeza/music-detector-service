package com.musicinfofinder.musicdetectorsrv.services.player;

import com.musicinfofinder.musicdetectorsrv.models.response.player.CurrentPlaying;

/**
 * Get information about the user's spotify player
 */
public interface IPlayerService {

    /**
     * Get the User's Currently Playing Track
     * @param userId current user id
     * @return CurrentPlaying which can be a track or an episode
     */
    CurrentPlaying getCurrentPlaying(String userId);
}
