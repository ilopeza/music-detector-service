package com.musicinfofinder.musicdetectorsrv.models.request.player;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.AbstractRequest;

public class CurrentPlayingRequest extends AbstractRequest {

    public CurrentPlayingRequest(CurrentPlayingRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected void validate() throws MalformedRequestException {

    }
}
