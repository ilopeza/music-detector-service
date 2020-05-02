package com.musicinfofinder.musicdetectorsrv.models.request;

public abstract class AbstractRequestBuilder<SELF extends IRequestBuilder<SELF,T>, T extends AbstractRequest>
				implements IRequestBuilder<SELF,T> {
	private String protocol;
	private String requestUri;
	private String endpoint;

	@Override
	public SELF withProtocol(String protocol) {
		this.protocol = protocol;
		return self();
	}

	@Override
	public SELF withRequestUri(String requestUri) {
		this.requestUri = requestUri;
		return self();
	}

	@Override
	public SELF withEndpoint(String endpoint) {
		this.endpoint = endpoint;
		return self();
	}

	@Override
	public T build() {
		validate();
		return internalBuild();
	}

	protected abstract T internalBuild();

	protected abstract void validate() throws IllegalArgumentException;

	private SELF self() {
		return (SELF) this;
	}
}
