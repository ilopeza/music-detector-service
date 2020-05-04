package com.musicinfofinder.musicdetectorsrv.models.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

public abstract class AbstractRequest implements IRequest {
	//TODO: SHOULD ADD QUERYPARAMS TO THE ABSTRACT FATHER AND REMOVE EXTRA FUNC FROM THE CHILDREN
	//TODO: SHOULD SUPPORT MULTIPLE PROTOCOLS
	private URI uri;
	private HttpHeaders headers;
	private MediaType contentType;
	private MultiValueMap<String, String> body;
	private MultiValueMap<String, String> queryParams;

	public AbstractRequest() {
		this.body = new LinkedMultiValueMap<>();
		this.queryParams = new LinkedMultiValueMap<>();
		this.headers = new HttpHeaders();
	}

	@Override
	public HttpHeaders getHeaders() {
		return headers;
	}

	@Override
	public MediaType getContentType() {
		return contentType;
	}

	@Override
	public final URI getUri() {
		return this.uri;
	}

	@Override
	public MultiValueMap<String, String> getBody() {
		return body;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	public void setContentType(MediaType contentType) {
		this.contentType = contentType;
	}

	public void setBody(MultiValueMap<String, String> body) {
		this.body = body;
	}
}
