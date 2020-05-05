package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
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

	public AbstractRequest(IRequestBuilder builder) {
		this();
		setUri(builder.buildUri());
		setBody(builder.getBody());
		setContentType(builder.getContentType());
		setHeaders(builder.getHeaders());
	}

	private AbstractRequest() {
		this.body = new LinkedMultiValueMap<>();
		this.queryParams = new LinkedMultiValueMap<>();
		this.headers = new HttpHeaders();
	}

	@Override
	public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	@Override
	public MediaType getContentType() {
		return contentType;
	}

	public void setContentType(MediaType contentType) {
		this.contentType = contentType;
	}

	@Override
	public final URI getUri() {
		return this.uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	@Override
	public MultiValueMap<String, String> getBody() {
		return body;
	}

	public void setBody(MultiValueMap<String, String> body) {
		this.body = body;
	}

	protected abstract void validate() throws AuthorizeException;
}
