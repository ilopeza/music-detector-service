package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;
import com.musicinfofinder.musicdetectorsrv.models.response.TokenResponse;
import org.springframework.web.client.RestClientException;

public interface IAuthorizationService {

	void authorize() throws AuthorizeException, MalformedRequestException;

	String postAuthorize(AuthorizeResponse response) throws AuthorizeException, MalformedRequestException;

	TokenResponse getToken(String code) throws AuthorizeException, MalformedRequestException;

	TokenResponse refreshToken() throws AuthorizeException, RestClientException, MalformedRequestException;
}
