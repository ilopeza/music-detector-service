package com.musicinfofinder.musicdetectorsrv.models.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Base64Utils;

import static com.musicinfofinder.musicdetectorsrv.models.request.TokenRequestBuilder.AUTHORIZATION_HEADER;
import static com.musicinfofinder.musicdetectorsrv.models.request.TokenRequestBuilder.GRANT_TYPE;

public class RefreshTokenRequestBuilder extends AbstractRequestBuilder<RefreshTokenRequestBuilder, TokenRequest> {

	private final static Logger logger = LogManager.getLogger(TokenRequestBuilder.class);

	public static RefreshTokenRequestBuilder requestBuilder(final String clientId, final String secretClient) {
		return new RefreshTokenRequestBuilder(clientId, secretClient);
	}

	private RefreshTokenRequestBuilder(final String clientId, final String clientSecret) {
		super();
		String valueToEncode = clientId + ":" + clientSecret;
		final String encoded = "Basic " + Base64Utils.encodeToString(valueToEncode.getBytes());
		logger.info("The authorization header is {}", encoded);
		withHeader(AUTHORIZATION_HEADER, encoded);
	}

	public RefreshTokenRequestBuilder withToken(final String token) {
		withBodyParameter("refresh_token", token);
		return this;
	}

	@Override
	public TokenRequest build() {
		withBodyParameter(GRANT_TYPE, "refresh_token");
		return new TokenRequest(this);
	}
}
