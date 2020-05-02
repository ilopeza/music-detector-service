package com.musicinfofinder.musicdetectorsrv.models.request;

public abstract class AbstractRequest {
	//TODO: SHOULD ADD QUERYPARAMS TO THE ABSTRACT FATHER AND REMOVE EXTRA FUNC FROM THE CHILDREN
	//TODO: SHOULD SUPPORT MULTIPLE PROTOCOLS
	public static final String HTTPS_PROTOCOL = "https://";
	private String protocol;
	private String requestUri;
	private String endpoint;

	public AbstractRequest(String requestUri, String endpoint) {
		this.requestUri = requestUri;
		this.endpoint = endpoint;
		this.protocol = HTTPS_PROTOCOL;
	}

	public String getBaseUri() {
		final StringBuilder uriBuilder = new StringBuilder()
						.append(protocol)
						.append(requestUri)
						.append(endpoint);
		return uriBuilder.toString();
	}
}
