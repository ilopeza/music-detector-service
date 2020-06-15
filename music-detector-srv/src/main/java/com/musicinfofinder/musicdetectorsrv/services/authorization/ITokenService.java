package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.Token;

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
    Token requestToken(String code) throws AuthorizeException, MalformedRequestException;

    /**
     * Will refresh token and update the information stored for the credentials.
     *
     * @param userId
     * @return
     * @throws AuthorizeException
     */
    Token refreshToken(String userId) throws AuthorizeException;

    /**
     * Calls to Spotify API /token with the appropriate parameters to get a new token.
     *
     * @param token
     * @return TokenDTO with the new token
     * @throws AuthorizeException
     * @throws MalformedRequestException
     */
    Token requestRefreshToken(String token) throws AuthorizeException, MalformedRequestException;

    /**
     * Get the token stored for the user. If the token is not valid, request the spotify api to refresh the token. If the
     * user credentials are not found, will throw an exception with the UNAUTHORIZED status.
     *
     * @param userId Id of the user
     * @return String with the token stored o the new one from the api
     */
    String getTokenForUser(String userId);
}
