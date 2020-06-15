package com.musicinfofinder.musicdetectorsrv.models.request.player;

import com.musicinfofinder.musicdetectorsrv.models.request.AbstractRequestBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;

import java.util.List;

import static com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder.HTTPS;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

public class CurrentPlayingRequestBuilder extends AbstractRequestBuilder<CurrentPlayingRequestBuilder, CurrentPlayingRequest> {
    public static final String API_SPOTIFY_HOST = "api.spotify.com";
    public static final String CURRENT_PLAYING_PATH = "v1/me/player/currently-playing";

    public static CurrentPlayingRequestBuilder getRequestBuilder(String token) {
        return new CurrentPlayingRequestBuilder(token);
    }

    private CurrentPlayingRequestBuilder(String token) {
        super();
        withHeader("Authorization", "Bearer " + token);
    }

    public CurrentPlayingRequestBuilder withMarket(String market) {
        withQueryParam("market", market);
        return this;
    }

    public CurrentPlayingRequestBuilder withAdditionalTypes(List<String> additionalTypes) {
        String types = StringUtils.join(additionalTypes, ",");
        withQueryParam("additional_types", types);
        return this;
    }

    @Override
    protected CurrentPlayingRequest internalBuild() {
        withScheme(HTTPS);
        withHost(API_SPOTIFY_HOST);
        withPath(CURRENT_PLAYING_PATH);
        withContentType(APPLICATION_JSON_UTF8);
        return new CurrentPlayingRequest(this);
    }
}
