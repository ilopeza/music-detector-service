package com.musicinfofinder.musicdetectorsrv.services;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;

public interface LoginService {

	void authorize() throws AuthorizeException;

	void postAuthorize(AuthorizeResponse response);
}