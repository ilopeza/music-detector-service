package com.musicinfofinder.musicdetectorsrv.models;

import com.musicinfofinder.musicdetectorsrv.enums.ResponseTypeEnum;
import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequestBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizeRequestTest {
	final static String REDIRECT_URI = "https://example.com/callback";
	final static String CLIENT_ID = "5fe01282e44241328a84e7c5cc169165";
	final static List<String> SCOPES = Arrays.asList("user-read-private", "user-read-email");
	private static final String STATE = "34fFs29kd09";

	@Test
	void when_getAuthorizeUri_should_be_equal() throws AuthorizeException {
		final String finalUri = "https://accounts.spotify.com/authorize" +
						"?client_id=5fe01282e44241328a84e7c5cc169165" +
						"&response_type=code" +
						"&redirect_uri=https%3A%2F%2Fexample.com%2Fcallback" +
						"&scope=user-read-private%20user-read-email" +
						"&state=34fFs29kd09";
		final AuthorizeRequest request = AuthorizeRequestBuilder
						.anAuthorizeRequest()
						.withClientId(CLIENT_ID)
						.withRedirectUri(REDIRECT_URI)
						.withResponseType(ResponseTypeEnum.CODE.getName())
						.withScopes(SCOPES)
						//.withShowDialog(false)
						.withState(STATE)
						.build();
		final String authorizeUri = request.getAuthorizeUri();
		assertEquals(finalUri, authorizeUri);
	}
}