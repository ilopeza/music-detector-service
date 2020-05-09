package com.musicinfofinder.musicdetectorsrv.models.request.token;

import com.musicinfofinder.musicdetectorsrv.models.request.AbstractRequestBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Base64Utils;

import static com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder.ACCOUNTS_SPOTIFY_HOST;
import static com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder.HTTPS;
import static com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder.AUTHORIZATION_HEADER;
import static com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder.GRANT_TYPE;
import static com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder.TOKEN_PATH;

public class RefreshTokenRequestBuilder extends AbstractRequestBuilder<RefreshTokenRequestBuilder, TokenRequest> {

	private final static Logger logger = LogManager.getLogger(TokenRequestBuilder.class);

	private RefreshTokenRequestBuilder(final String clientId, final String clientSecret) {
		super();
		String valueToEncode = clientId + ":" + clientSecret;
		final String encoded = "Basic " + Base64Utils.encodeToString(valueToEncode.getBytes());
		logger.info("The authorization header is {}", encoded);
		withHeader(AUTHORIZATION_HEADER, encoded);
	}

	public static RefreshTokenRequestBuilder requestBuilder(final String clientId, final String secretClient) {
		return new RefreshTokenRequestBuilder(clientId, secretClient);
	}

	public RefreshTokenRequestBuilder withRefreshToken(final String token) {
		withBodyParameter("refresh_token", token);
		return this;
	}

	@Override
	protected TokenRequest internalBuild() {
		withScheme(HTTPS);
		withHost(ACCOUNTS_SPOTIFY_HOST);
		withPath(TOKEN_PATH);
		withBodyParameter(GRANT_TYPE, "refresh_token");
		return new TokenRequest(this);
	}
}
