package com.musicinfofinder.musicdetectorsrv.models.request;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isEmpty;

public class AuthorizeRequestBuilder extends AbstractRequestBuilder<AuthorizeRequestBuilder, AuthorizeRequest> {
	public static final String ACCOUNTS_SPOTIFY_URI = "accounts.spotify.com/";
	public static final String AUTHORIZE_ENDPOINT = "authorize";

	private String clientId;
	private String redirectUri;
	private List<String> scopes;
	private String state;
	private String responseType;
	private Boolean showDialog;

	public static AuthorizeRequestBuilder anAuthorizeRequest() {
		return new AuthorizeRequestBuilder();
	}

	public AuthorizeRequestBuilder withClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public AuthorizeRequestBuilder withRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
		return this;
	}

	public AuthorizeRequestBuilder withScopes(List<String> scopes) {
		this.scopes = scopes;
		return this;
	}

	public AuthorizeRequestBuilder withState(String state) {
		this.state = state;
		return this;
	}

	public AuthorizeRequestBuilder withResponseType(String responseType) {
		this.responseType = responseType;
		return this;
	}

	public AuthorizeRequestBuilder withShowDialog(Boolean showDialog) {
		this.showDialog = showDialog;
		return this;
	}

	@Override
	protected AuthorizeRequest internalBuild() {
		AuthorizeRequest authorizeRequest = new AuthorizeRequest(ACCOUNTS_SPOTIFY_URI, AUTHORIZE_ENDPOINT);

		authorizeRequest.setClientId(this.clientId);
		authorizeRequest.setRedirectUri(this.redirectUri);
		authorizeRequest.setState(this.state);
		authorizeRequest.setScopes(this.scopes);
		authorizeRequest.setShowDialog(this.showDialog);
		authorizeRequest.setResponseType(this.responseType);

		return authorizeRequest;
	}

	@Override
	protected void validate() throws IllegalArgumentException {
		if (isEmpty(clientId)) {
			throw new IllegalArgumentException("Redirect uri is required");
		}
		if (isEmpty(redirectUri)) {
			throw new IllegalArgumentException("Redirect uri is required");
		}
		if (isEmpty(responseType)) {
			throw new IllegalArgumentException("Response type is required");
		}
	}
}
