package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import org.apache.commons.lang.StringUtils;

import java.net.URI;

import static com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequestBuilder.ACCOUNTS_SPOTIFY_HOST;
import static com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequestBuilder.AUTHORIZE_PATH;
import static com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequestBuilder.HTTPS;

public class AuthorizeRequest extends AbstractRequest {
	public AuthorizeRequest(AuthorizeRequestBuilder authorizeRequestBuilder) {
		super(authorizeRequestBuilder);
	}

	@Override
	protected void validate() throws AuthorizeException {
		final URI uri = getUri();
		if (StringUtils.equalsIgnoreCase(uri.getScheme(), HTTPS)) {
			throw new AuthorizeException("Scheme " + uri.getScheme() +" is not valid. Should be " + HTTPS);
		}
		if (StringUtils.equalsIgnoreCase(uri.getPath(), AUTHORIZE_PATH)) {
			throw new AuthorizeException("Path " + uri.getPath() +" is not valid. Should be " + AUTHORIZE_PATH);
		}
		if (StringUtils.equalsIgnoreCase(uri.getHost(), ACCOUNTS_SPOTIFY_HOST)) {
			throw new AuthorizeException("Host " + uri.getHost() +" is not valid. Should be " + ACCOUNTS_SPOTIFY_HOST);
		}
		//query params
		final String query = getUri().getQuery();
		if (StringUtils.isEmpty(query)) {
			throw new AuthorizeException("Empty query params.");
		}
	}
}
