package com.musicinfofinder.musicdetectorsrv.models.request.player;

import com.musicinfofinder.musicdetectorsrv.models.request.AbstractRequestBuilder;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import static com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder.HTTPS;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

public class CurrentPlayingRequestBuilder extends AbstractRequestBuilder<CurrentPlayingRequestBuilder, CurrentPlayingRequest> {
    public static final String API_SPOTIFY_HOST = "api.spotify.com";
    public static final String CURRENT_PLAYING_PATH = "v1/me/player/currently-playing";

    private CurrentPlayingRequestBuilder(String token) {
        super();
        withHeader("Authorization", "Bearer " + token);
    }

    public static CurrentPlayingRequestBuilder getRequestBuilder(String token) {
        return new CurrentPlayingRequestBuilder(token);
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
