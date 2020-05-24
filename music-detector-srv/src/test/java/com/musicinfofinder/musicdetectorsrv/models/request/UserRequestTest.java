package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.user.UserRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.user.UserRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRequestTest {

    @Test
    public void get_uri_should_be_equal() throws MalformedRequestException {
        String finalUri = "https://api.spotify.com/v1/me";
        final UserRequest userRequest = UserRequestBuilder.getRequestBuilder("BQAqFXTeJ5JR56em-SeseqJ6XArRH58deu5iLjse5xz_pq3cgYHumovCSG8FoKBf4bXYj6PwPbjttafw2atq0CXjjx1qhoWoLvsyzMD3UUY1LqPP_PFecWRYBpqth7dxDNkMlEUKpUO0AD5QiNaiN-gmDsdVwSpf")
                .build();
        final String uri = userRequest.getUri().toString();
        assertEquals(uri, finalUri);
    }
}
