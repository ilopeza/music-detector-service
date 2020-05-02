package com.musicinfofinder.musicdetectorsrv.models.request;

public interface IRequestBuilder<SELF extends IRequestBuilder<SELF,T>, T extends AbstractRequest> {
	SELF withProtocol(String protocol);

	SELF withRequestUri(String requestUri);

	SELF withEndpoint(String endpoint);

	T build();
}
