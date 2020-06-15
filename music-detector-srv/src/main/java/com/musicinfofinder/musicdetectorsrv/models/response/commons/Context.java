package com.musicinfofinder.musicdetectorsrv.models.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.player.ObjectTypeEnum;
import lombok.Data;

/***
 * A context object for modeling responses
 * @see : https://developer.spotify.com/documentation/web-api/reference/player/get-the-users-currently-playing-track/
 */
@Data
public class Context {
    @JsonProperty("ury")
    private String uri; //The uri of the context.
    @JsonProperty("href")
    private String href; //The href of the context, or null if not available.
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls; // An External URL Object	The external_urls of the context, or null if not available.
    @JsonProperty("type")
    private ObjectTypeEnum type; //The object type of the item’s context. Can be one of album , artist or playlist.
}
