package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;

/**
 * Service to interact with the Spotify token API
 */
public interface ITokenService {

	/**
	 * Calls to Spotify API accounts.spotify.com/api/token to get the token.
	 *
	 * @param code Application code obtained after authorization.
	 * @return TokenDTO
	 * @throws AuthorizeException
	 * @throws MalformedRequestException
	 */
	TokenDTO requestToken(String code) throws AuthorizeException, MalformedRequestException;

	/**
	 * Will refresh token and update the information stored for the credentials.
	 * @param userId
	 * @return
	 * @throws AuthorizeException
	 */
	TokenDTO refreshToken(String userId) throws AuthorizeException;

	/**
	 * Calls to Spotify API /token with the appropriate parameters to get a new token.
	 *
	 * @param token
	 * @return TokenDTO with the new token
	 * @throws AuthorizeException
	 * @throws MalformedRequestException
	 */
	TokenDTO requestRefreshToken(String token) throws AuthorizeException, MalformedRequestException;
}