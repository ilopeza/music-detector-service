package com.musicinfofinder.musicdetectorsrv.models.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.net.URI;

public interface IRequest {

    HttpHeaders getHeaders();

    URI getUri();

    MediaType getContentType();

    MultiValueMap<String, String> getBody();
}
