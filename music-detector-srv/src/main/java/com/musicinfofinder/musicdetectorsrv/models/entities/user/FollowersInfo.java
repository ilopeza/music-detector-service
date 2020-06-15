package com.musicinfofinder.musicdetectorsrv.models.entities.user;

import lombok.Data;

/**
 * Contains information about the user's followers.
 *
 * @see :https://developer.spotify.com/documentation/web-api/reference/object-model/#followers-object
 */
@Data
public class FollowersInfo {
    private String href;
    private int total;
}
