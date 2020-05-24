package com.musicinfofinder.musicdetectorsrv.models.request.user;

import com.musicinfofinder.musicdetectorsrv.models.request.AbstractRequestBuilder;

import static com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder.HTTPS;

public class UserRequestBuilder extends AbstractRequestBuilder<UserRequestBuilder, UserRequest> {
    public static final String API_SPOTIFY_HOST = "api.spotify.com";
    public static final String ME_PATH = "v1/me";

    private UserRequestBuilder(String token) {
        super();
        withHeader("Authorization", "Bearer " + token);
    }

    public static UserRequestBuilder getRequestBuilder(String token) {
        return new UserRequestBuilder(token);
    }

    @Override
    protected UserRequest internalBuild() {
        withScheme(HTTPS);
        withHost(API_SPOTIFY_HOST);
        withPath(ME_PATH);
        return new UserRequest(this);
    }
}
