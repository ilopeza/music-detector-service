package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.collectionToDelimitedString;
import static org.springframework.util.StringUtils.isEmpty;

public class AuthorizeRequest extends AbstractRequest {

	public static final String UTF_8 = "UTF-8";

	private String clientId;
	private String redirectUri;
	private List<String> scopes;
	private String state;
	private String responseType;
	private Boolean showDialog;

	public AuthorizeRequest(String requestUri, String endpoint) {
		super(requestUri, endpoint);
	}

	public String getAuthorizeUri() throws AuthorizeException {
		final String encodedRedirectUri;
		try {
			encodedRedirectUri = URLEncoder.encode(redirectUri, UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new AuthorizeException("Encoding of redirect uri is not supported", e);
		}
		final String baseUri = super.getBaseUri();
		final StringBuilder uriBuilder = new StringBuilder()
						.append(baseUri)
						.append("?client_id=")
						.append(clientId)
						.append("&response_type=")
						.append(responseType)
						.append("&redirect_uri=")
						.append(encodedRedirectUri);

		if (!CollectionUtils.isEmpty(scopes)) {
			uriBuilder.append("&scope=")
							.append(collectionToDelimitedString(scopes, "%20"));
		}
		if (!isEmpty(state)) {
			uriBuilder.append("&state=")
							.append(state);
		}
		if (!isNull(showDialog)) {
			uriBuilder.append("&show_dialog=")
							.append(showDialog);
		}
		return uriBuilder.toString();
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public void setScopes(List<String> scopes) {
		this.scopes = scopes;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public void setShowDialog(Boolean showDialog) {
		this.showDialog = showDialog;
	}
}
