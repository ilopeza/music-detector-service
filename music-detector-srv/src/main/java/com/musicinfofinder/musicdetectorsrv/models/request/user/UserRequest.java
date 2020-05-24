package com.musicinfofinder.musicdetectorsrv.models.request.user;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.AbstractRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.IRequestBuilder;

public class UserRequest extends AbstractRequest {

    public UserRequest(IRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected void validate() throws MalformedRequestException {
    }
}
