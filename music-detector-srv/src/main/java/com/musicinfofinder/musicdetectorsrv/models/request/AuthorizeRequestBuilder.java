package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.services.AuthorizationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static org.apache.commons.lang.CharEncoding.UTF_8;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

public class AuthorizeRequestBuilder extends AbstractRequestBuilder<AuthorizeRequestBuilder, AuthorizeRequest> {
	public static final String HTTPS = "https";
	public static final String ACCOUNTS_SPOTIFY_URI = "accounts.spotify.com";
	public static final String AUTHORIZE_ENDPOINT = "authorize";
	public static final String CLIENT_ID_KEY = "client_id";
	public static final String REDIRECT_URI_KEY = "redirect_uri";
	public static final String SCOPE_KEY = "scope";
	public static final String STATE_KEY = "state";
	public static final String RESPONSE_TYPE_KEY = "response_type";
	public static final String SHOW_DIALOG_KEY = "show_dialog";
	private final static Logger logger = LogManager.getLogger(AuthorizationServiceImpl.class);

	private AuthorizeRequestBuilder() {
		super();
		withScheme(HTTPS);
		withPath(AUTHORIZE_ENDPOINT);
		withHost(ACCOUNTS_SPOTIFY_URI);
	}

	public static AuthorizeRequestBuilder requestBuilder() {
		return new AuthorizeRequestBuilder();
	}

	public AuthorizeRequestBuilder withClientId(String clientId) {
		withQueryParam(CLIENT_ID_KEY, clientId);
		return this;
	}

	public AuthorizeRequestBuilder withRedirectUri(String redirectUri) {
		String encodedRedirectUri = null;
		try {
			encodedRedirectUri = URLEncoder.encode(redirectUri, UTF_8);
		} catch (UnsupportedEncodingException exception) {
			logger.error("Uri could not be encoded", exception);
		}
		//this.encodedRedirectUri = encodedRedirectUri;**/
		withQueryParam(REDIRECT_URI_KEY, encodedRedirectUri);
		return this;
	}

	public AuthorizeRequestBuilder withScopes(List<String> scopes) {
		final String delimitedScopes = collectionToDelimitedString(scopes, "%20");
		withQueryParam(SCOPE_KEY, delimitedScopes);
		return this;
	}

	public AuthorizeRequestBuilder withState(String state) {
		withQueryParam(STATE_KEY, state);
		return this;
	}

	public AuthorizeRequestBuilder withResponseType(String responseType) {
		withQueryParam(RESPONSE_TYPE_KEY, responseType);
		return this;
	}

	public AuthorizeRequestBuilder withShowDialog(Boolean showDialog) {
		withQueryParam(SHOW_DIALOG_KEY, showDialog.toString().toLowerCase());
		return this;
	}

	@Override
	protected AuthorizeRequest internalBuild() {
		AuthorizeRequest authorizeRequest = new AuthorizeRequest();
		return authorizeRequest;
	}

	@Override
	protected void validate() throws IllegalArgumentException {
	}
}
