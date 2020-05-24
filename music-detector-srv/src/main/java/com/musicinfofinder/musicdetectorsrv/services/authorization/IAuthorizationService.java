package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;

/**
 * Service to call the spotify authorization api.
 */
public interface IAuthorizationService {

    /**
     * Calls Spotify API to start the authentication flow.
     *
     * @throws AuthorizeException
     * @throws MalformedRequestException
     */
    void authorize() throws AuthorizeException, MalformedRequestException;

    /**
     * Called after the user explicitly authorizes the access to the Spotify account.
     *
     * @param response
     * @return
     * @throws AuthorizeException
     * @throws MalformedRequestException
     */
    String postAuthorize(AuthorizationDTO response) throws AuthorizeException, MalformedRequestException;
}
