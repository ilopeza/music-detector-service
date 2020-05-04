package com.musicinfofinder.musicdetectorsrv.models.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;

import static com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequestBuilder.HTTPS;

public class TokenRequestBuilder extends AbstractRequestBuilder<TokenRequestBuilder, TokenRequest> {
	public static final String ACCOUNTS_SPOTIFY_URI = "accounts.spotify.com";
	public static final String GRANT_TYPE = "grant_type";
	public static final String CODE = "code";
	public static final String REDIRECT_URI = "redirect_uri";
	public static final String API_TOKEN = "api/token";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	private final static Logger logger = LogManager.getLogger(TokenRequestBuilder.class);
	private static final String AUTH_GRANT_TYPE = "authorization_code";

	private TokenRequestBuilder(String clientId, String clientSecret) {
		super();
		withPath(API_TOKEN);
		withHost(ACCOUNTS_SPOTIFY_URI);
		withBodyParameter(GRANT_TYPE, AUTH_GRANT_TYPE);
		withScheme(HTTPS);
		withContentType(MediaType.APPLICATION_FORM_URLENCODED);

		String valueToEncode = clientId + ":" + clientSecret;
		final String encoded = "Basic " + Base64Utils.encodeToString(valueToEncode.getBytes());
		logger.info("The authorization header is {}", encoded);
		withHeader(AUTHORIZATION_HEADER, encoded);
	}

	public static TokenRequestBuilder requestBuilder(final String clientId, final String clientSecret) {
		return new TokenRequestBuilder(clientId, clientSecret);
	}

	public TokenRequestBuilder withCode(String code) {
		withBodyParameter(CODE, code);
		return this;
	}

	public TokenRequestBuilder withRedirectUri(String uri) {
		withBodyParameter(REDIRECT_URI, uri);
		return this;
	}

	@Override
	protected TokenRequest internalBuild() {
		TokenRequest tokenRequest = new TokenRequest();
		return tokenRequest;
	}

	@Override
	protected void validate() throws IllegalArgumentException {
	}
}