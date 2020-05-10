package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.entities.authentication.Authentication;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;

import java.util.Optional;

public interface IAuthorizationService {

	void authorize() throws AuthorizeException, MalformedRequestException;

	String postAuthorize(AuthorizationDTO response) throws AuthorizeException, MalformedRequestException;

	TokenDTO requestToken(String code) throws AuthorizeException, MalformedRequestException;

	TokenDTO refreshToken(String userId) throws AuthorizeException;

	TokenDTO requestRefreshToken(String token) throws AuthorizeException, MalformedRequestException;

	Authentication save(String userId, String code, TokenDTO token);

	Optional<Authentication> getAuthenticationByUserId(String userId);
}
