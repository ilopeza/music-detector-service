package com.musicinfofinder.musicdetectorsrv.services;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;

public interface AuthorizationService {

	void authorize() throws AuthorizeException;

	String postAuthorize(AuthorizeResponse response) throws AuthorizeException;

	String getToken(String code);

	String refreshToken();
}
