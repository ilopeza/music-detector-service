package com.musicinfofinder.musicdetectorsrv.models.request.token;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.AbstractRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.IRequestBuilder;
import org.springframework.http.HttpHeaders;

import java.net.URI;

import static com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder.ACCOUNTS_SPOTIFY_HOST;
import static com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder.HTTPS;
import static com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder.AUTHORIZATION_HEADER;
import static com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder.TOKEN_PATH;
import static java.util.Objects.isNull;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isEmpty;

public class TokenRequest extends AbstractRequest {

    public TokenRequest(IRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected void validate() throws MalformedRequestException {
        //validate uri
        final URI uri = getUri();
        if (!equalsIgnoreCase(uri.getScheme(), HTTPS)) {
            throw new MalformedRequestException("Scheme " + uri.getScheme() + " is not valid. Should be " + HTTPS);
        }
        if (!equalsIgnoreCase(uri.getPath(), "/" + TOKEN_PATH)) {
            throw new MalformedRequestException("Path " + uri.getPath() + " is not valid. Should be " + TOKEN_PATH);
        }
        if (!equalsIgnoreCase(uri.getHost(), ACCOUNTS_SPOTIFY_HOST)) {
            throw new MalformedRequestException("Host " + uri.getHost() + " is not valid. Should be " + ACCOUNTS_SPOTIFY_HOST);
        }
        //headers
        final HttpHeaders headers = getHeaders();
        if (isNull(headers)
                || !headers.containsKey(AUTHORIZATION_HEADER)
                || isEmpty(headers.get(AUTHORIZATION_HEADER).get(0))) {
            throw new MalformedRequestException("Authorization header is required");
        }
    }
}
