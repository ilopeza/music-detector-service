package com.musicinfofinder.musicdetectorsrv.models.request;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.apache.commons.lang.StringUtils.isEmpty;

public abstract class AbstractRequestBuilder<SELF extends IRequestBuilder<SELF, T>, T extends AbstractRequest>
        implements IRequestBuilder<SELF, T> {
    private final HttpHeaders headers;
    private final MultiValueMap<String, String> queryParams;
    protected MultiValueMap<String, String> body;
    private String scheme;
    private String host;
    private String path;
    private MediaType contentType;

    protected AbstractRequestBuilder() {
        this.body = new LinkedMultiValueMap<>();
        this.queryParams = new LinkedMultiValueMap<>();
        this.headers = new HttpHeaders();
    }

    @Override
    public SELF withScheme(final String protocol) {
        this.scheme = protocol;
        return self();
    }

    @Override
    public SELF withHost(final String requestUri) {
        this.host = requestUri;
        return self();
    }

    @Override
    public SELF withPath(final String endpoint) {
        this.path = endpoint;
        return self();
    }

    public SELF withQueryParam(final String name, final String value) {
        queryParams.set(name, value);
        return self();
    }

    public SELF withBodyParameter(final String name, final String value) {
        body.set(name, value);
        return self();
    }

    public SELF withHeader(final String name, final String value) {
        if (isEmpty(name) || isEmpty(value)) {
            throw new IllegalArgumentException("Header with name " + name + " and value " + value + "is not valid");
        }
        headers.set(name, value);
        return self();
    }

    public SELF withContentType(final MediaType mediaType) {
        this.contentType = mediaType;
        return self();
    }

    @Override
    public MultiValueMap<String, String> getBody() {
        return body;
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
    public URI buildUri() {
        final UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .path(path)
                .queryParams(queryParams)
                .build(true);
        final URI uri = uriComponents.toUri();
        return uri;
    }

    protected abstract T internalBuild();

    public T build() throws MalformedRequestException {
        final T t = internalBuild();
        t.validate();
        return t;
    }

    private SELF self() {
        return (SELF) this;
    }
}
