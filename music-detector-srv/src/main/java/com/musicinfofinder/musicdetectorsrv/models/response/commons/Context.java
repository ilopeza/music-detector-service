package com.musicinfofinder.musicdetectorsrv.models.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.player.ObjectTypeEnum;

/***
 * A context object for modeling responses
 * @see : https://developer.spotify.com/documentation/web-api/reference/player/get-the-users-currently-playing-track/
 */
public class Context {
    @JsonProperty("ury")
    private String uri; //The uri of the context.
    @JsonProperty("href")
    private String href; //The href of the context, or null if not available.
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls; // An External URL Object	The external_urls of the context, or null if not available.
    @JsonProperty("type")
    private ObjectTypeEnum type; //The object type of the item’s context. Can be one of album , artist or playlist.

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ExternalUrl getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrl externalUrls) {
        this.externalUrls = externalUrls;
    }

    public ObjectTypeEnum getType() {
        return type;
    }

    public void setType(ObjectTypeEnum type) {
        this.type = type;
    }
}
