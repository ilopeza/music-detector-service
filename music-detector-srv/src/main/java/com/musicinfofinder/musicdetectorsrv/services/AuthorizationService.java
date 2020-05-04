package com.musicinfofinder.musicdetectorsrv.services;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;
import com.musicinfofinder.musicdetectorsrv.models.response.TokenResponse;

public interface AuthorizationService {

	void authorize() throws AuthorizeException;

	String postAuthorize(AuthorizeResponse response) throws AuthorizeException;

	TokenResponse getToken(String code);

	TokenResponse refreshToken();
}
