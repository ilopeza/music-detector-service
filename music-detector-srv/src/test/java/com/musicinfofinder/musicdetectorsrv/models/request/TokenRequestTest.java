package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TokenRequestTest {
	public static final String CODE_KEY = "code";
	private final static String REDIRECT_URI = "https://example.com/callback";
	private final static String CLIENT_ID = "5fe01282e44241328a84e7c5cc169165";
	private final static String SECRET_CLIENT = "23cc910c0039432b89f8fda30a4a0592";
	private final static String CODE = "AQBx7vfSfTc44yxPCn4d6euXCPhgn17yl71MNB6quee95Be5oZnlseMOiRzqBP-RXO43vj42MfGJVafkWJs-PaDj8mlFLlDlWVzGqWkUQPvC7gg301QdNTsqYhhLeoFnl3o6Q1_EpXnoJJcEFjmP4oKMgkSjzUB5AD7StS4AmMbx96M0HAjBV0OSUoqteqwLCbFGyHWrrQgI8Pr_T7hDMDjfOz00f_3ZERyfUTxqUE6Jlt3xQxGwvXuP_EctaH3OhiSwa1ISh2Ao2chVvLgQZsn5h-fNptI8ZdsokGLronFqzXlLGYJNaRSk5NGNWi95xiSXzw";
	private final static String ENCODED_AUTH_HEADER = "Basic NWZlMDEyODJlNDQyNDEzMjhhODRlN2M1Y2MxNjkxNjU6MjNjYzkxMGMwMDM5NDMyYjg5ZjhmZGEzMGE0YTA1OTI=";
	private static final String AUTH_GRANT_TYPE = "authorization_code";
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String GRANT_TYPE_KEY = "grant_type";
	private static final String REDIRECT_URI_KEY = "redirect_uri";

	@Test
	void when_getUri_should_be_equal() throws MalformedRequestException {
		final String finalUri = "https://accounts.spotify.com/api/token";
		final TokenRequest tokenRequest = TokenRequestBuilder.requestBuilder(CLIENT_ID, SECRET_CLIENT)
						.withCode(CODE)
						.withRedirectUri(REDIRECT_URI)
						.build();
		final URI uri = tokenRequest.getUri();
		final String authorizeUri = uri.toString();
		assertEquals(finalUri, authorizeUri);
	}

	@Test
	void when_create_token_request_should_have_auth_header() throws MalformedRequestException {
		final TokenRequest tokenRequest = TokenRequestBuilder.requestBuilder(CLIENT_ID, SECRET_CLIENT)
						.withCode(CODE)
						.withRedirectUri(REDIRECT_URI)
						.build();
		assertNotNull(tokenRequest.getHeaders().get(AUTHORIZATION_HEADER_KEY).get(0));
	}

	@Test
	void when_create_token_request_should_have_auth_header_equals_to_predefined() throws MalformedRequestException {
		final TokenRequest tokenRequest = TokenRequestBuilder.requestBuilder(CLIENT_ID, SECRET_CLIENT)
						.withCode(CODE)
						.withRedirectUri(REDIRECT_URI)
						.build();
		final String authHeader = tokenRequest.getHeaders().get(AUTHORIZATION_HEADER_KEY).get(0);
		assertEquals(ENCODED_AUTH_HEADER, authHeader);
	}

	@Test
	void when_create_token_request_should_have_required_body_params() throws MalformedRequestException {
		final TokenRequest tokenRequest = TokenRequestBuilder.requestBuilder(CLIENT_ID, SECRET_CLIENT)
						.withCode(CODE)
						.withRedirectUri(REDIRECT_URI)
						.build();

		assertNotNull(tokenRequest.getBody());
		assertNotNull(tokenRequest.getBody().get(GRANT_TYPE_KEY));
		assertNotNull(tokenRequest.getBody().get(REDIRECT_URI_KEY));
		assertNotNull(tokenRequest.getBody().get(CODE_KEY));

		assertEquals(AUTH_GRANT_TYPE, tokenRequest.getBody().get(GRANT_TYPE_KEY).get(0));
		assertEquals(REDIRECT_URI, tokenRequest.getBody().get(REDIRECT_URI_KEY).get(0));
		assertEquals(CODE, tokenRequest.getBody().get(CODE_KEY).get(0));
	}
}