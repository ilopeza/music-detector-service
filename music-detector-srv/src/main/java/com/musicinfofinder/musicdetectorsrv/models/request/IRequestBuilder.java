package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.net.URI;

public interface IRequestBuilder<SELF extends IRequestBuilder<SELF, T>, T extends AbstractRequest> {
	SELF withScheme(final String protocol);

	SELF withHost(final String requestUri);

	SELF withPath(final String endpoint);

	SELF withHeader(final String name, final String value);

	SELF withBodyParameter(final String name, final String value);

	SELF withQueryParam(final String name, final String value);

	SELF withContentType(final MediaType mediaType);

	T build() throws AuthorizeException;

	URI buildUri();

	MultiValueMap<String, String> getBody();

	HttpHeaders getHeaders();

	MediaType getContentType();
}
