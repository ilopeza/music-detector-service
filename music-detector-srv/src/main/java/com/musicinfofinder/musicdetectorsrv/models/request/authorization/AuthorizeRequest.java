package com.musicinfofinder.musicdetectorsrv.models.request.authorization;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.AbstractRequest;
import org.apache.commons.lang.StringUtils;

import java.net.URI;

import static com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder.*;

public class AuthorizeRequest extends AbstractRequest {
    public AuthorizeRequest(AuthorizeRequestBuilder authorizeRequestBuilder) {
        super(authorizeRequestBuilder);
    }

    @Override
    protected void validate() throws MalformedRequestException {
        final URI uri = getUri();
        if (!StringUtils.equalsIgnoreCase(uri.getScheme(), HTTPS)) {
            throw new MalformedRequestException("Scheme " + uri.getScheme() + " is not valid. Should be " + HTTPS);
        }
        if (!StringUtils.equalsIgnoreCase(uri.getPath(), "/" + AUTHORIZE_PATH)) {
            throw new MalformedRequestException("Path " + uri.getPath() + " is not valid. Should be " + AUTHORIZE_PATH);
        }
        if (!StringUtils.equalsIgnoreCase(uri.getHost(), ACCOUNTS_SPOTIFY_HOST)) {
            throw new MalformedRequestException("Host " + uri.getHost() + " is not valid. Should be " + ACCOUNTS_SPOTIFY_HOST);
        }
        //query params
        final String query = getUri().getQuery();
        if (StringUtils.isEmpty(query)) {
            throw new MalformedRequestException("Empty query params.");
        }
    }
}
