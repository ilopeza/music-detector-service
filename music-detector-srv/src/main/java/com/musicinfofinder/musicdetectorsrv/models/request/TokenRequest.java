package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import org.springframework.http.HttpHeaders;

import java.net.URI;

import static com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequestBuilder.ACCOUNTS_SPOTIFY_HOST;
import static com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequestBuilder.HTTPS;
import static com.musicinfofinder.musicdetectorsrv.models.request.TokenRequestBuilder.API_TOKEN;
import static com.musicinfofinder.musicdetectorsrv.models.request.TokenRequestBuilder.AUTHORIZATION_HEADER;
import static java.util.Objects.isNull;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isEmpty;

public class TokenRequest extends AbstractRequest {

	public TokenRequest(IRequestBuilder builder) {
		super(builder);
	}

	@Override
	protected void validate() throws AuthorizeException {
		//validate uri
		final URI uri = getUri();
		if (equalsIgnoreCase(uri.getScheme(), HTTPS)) {
			throw new AuthorizeException("Scheme " + uri.getScheme() + " is not valid. Should be " + HTTPS);
		}
		if (equalsIgnoreCase(uri.getPath(), API_TOKEN)) {
			throw new AuthorizeException("Path " + uri.getPath() + " is not valid. Should be " + API_TOKEN);
		}
		if (equalsIgnoreCase(uri.getHost(), ACCOUNTS_SPOTIFY_HOST)) {
			throw new AuthorizeException("Host " + uri.getHost() + " is not valid. Should be " + ACCOUNTS_SPOTIFY_HOST);
		}

		//query params
		final String query = getUri().getQuery();
		if (isEmpty(query)) {
			throw new AuthorizeException("Empty query params.");
		}

		//headers
		final HttpHeaders headers = getHeaders();
		if (isNull(headers)
						|| !headers.containsKey(AUTHORIZATION_HEADER)
						|| isEmpty(headers.get(AUTHORIZATION_HEADER).get(0))) {
			throw new AuthorizeException("Authorization header is required");
		}
	}
}
