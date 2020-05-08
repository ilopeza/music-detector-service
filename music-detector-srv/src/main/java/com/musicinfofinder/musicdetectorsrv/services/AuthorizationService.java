package com.musicinfofinder.musicdetectorsrv.services;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;
import com.musicinfofinder.musicdetectorsrv.models.response.TokenResponse;
import org.springframework.web.client.RestClientException;

public interface AuthorizationService {

	void authorize() throws AuthorizeException;

	String postAuthorize(AuthorizeResponse response) throws AuthorizeException;

	TokenResponse getToken(String code) throws AuthorizeException;

	TokenResponse refreshToken(String refreshToken) throws AuthorizeException, RestClientException;
}
