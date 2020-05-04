package com.musicinfofinder.musicdetectorsrv.models.request;

import org.springframework.http.MediaType;

public interface IRequestBuilder<SELF extends IRequestBuilder<SELF, T>, T extends AbstractRequest> {
	SELF withScheme(final String protocol);

	SELF withHost(final String requestUri);

	SELF withPath(final String endpoint);

	SELF withHeader(final String name, final String value);

	SELF withBodyParameter(final String name, final String value);

	SELF withQueryParam(final String name, final String value);

	SELF withContentType(final MediaType mediaType);

	T build();
}
