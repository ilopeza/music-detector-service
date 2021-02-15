package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.config.SpotifyCredentials;
import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceImplTest {

    /*private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String URI_REQUEST = "http://localhost:8080";*/
    IAuthorizationService authorizationService;
    @Mock
    AuthorizationDTO authorizationDTO;
    @Mock
    SpotifyCredentials spotifyCredentials;
    @Mock
    TokenServiceImpl iTokenService;

    @BeforeEach
    void initMocks() {
        authorizationService = new AuthorizationServiceImpl(iTokenService, spotifyCredentials);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void when_user_does_not_authorize_postAuthorize_should_throw_exception() {
        when(authorizationDTO.hasError()).thenReturn(true);
        assertThrows(AuthorizeException.class, () ->
                authorizationService.postAuthorize(authorizationDTO));
    }

}