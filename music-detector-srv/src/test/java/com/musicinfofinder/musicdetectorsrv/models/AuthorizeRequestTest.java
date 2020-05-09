package com.musicinfofinder.musicdetectorsrv.models;

import com.musicinfofinder.musicdetectorsrv.enums.ResponseTypeEnum;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizeRequestTest {
	final static String REDIRECT_URI = "https://example.com/callback";
	final static String CLIENT_ID = "5fe01282e44241328a84e7c5cc169165";
	final static List<String> SCOPES = Arrays.asList("user-read-private", "user-read-email");
	private static final String STATE = "34fFs29kd09";

	@Test
	void when_getAuthorizeUri_should_be_equal() throws MalformedRequestException {
		final String finalUri = "https://accounts.spotify.com/authorize" +
						"?client_id=5fe01282e44241328a84e7c5cc169165" +
						"&response_type=code" +
						"&redirect_uri=https%3A%2F%2Fexample.com%2Fcallback" +
						"&scope=user-read-private%20user-read-email" +
						"&state=34fFs29kd09";
		final AuthorizeRequest request = AuthorizeRequestBuilder
						.requestBuilder()
						.withClientId(CLIENT_ID)
						.withResponseType(ResponseTypeEnum.CODE.getName())
						.withRedirectUri(REDIRECT_URI)
						.withScopes(SCOPES)
						//.withShowDialog(false)
						.withState(STATE)
						.build();
		final URI uri = request.getUri();
		final String authorizeUri = uri.toString();
		assertEquals(finalUri, authorizeUri);
	}
}