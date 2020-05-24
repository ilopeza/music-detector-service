package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.InvalidParameterException;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.Token;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserCredentialsDTO;
import com.musicinfofinder.musicdetectorsrv.services.credentials.IUserCredentialsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {
    private static final String USER_ID = "user_id";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String NEW_TOKEN = "new_token";
    private static final int EXPIRES_IN = 3600;
    private static final String TOKEN = "current_token";
    private static final URI URI_REFRESH_TOKEN = URI.create("http://localhost.com:8080/refresh-token");

    @Mock
    UserCredentials userCredentials;

    @Mock
    TokenDTO refreshToken;

    @Mock
    RestTemplate restTemplate;

    @Mock
    IUserCredentialsService userCredentialsService;

    @InjectMocks
    ITokenService tokenService = new TokenServiceImpl();

    @Test
    void when_request_refresh_token_with_null_user_id_should_throw_exception() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class, () -> tokenService.refreshToken(null));
    }

    @Test
    void when_request_refresh_token_with_empty_user_id_should_throw_exception() throws InvalidParameterException {
        assertThrows(InvalidParameterException.class, () -> tokenService.refreshToken(""));
    }

    @Test
    void when_request_refresh_token_with_non_existing_user_should_throw_exception() throws AuthorizeException {
        when(userCredentialsService.get(USER_ID)).thenReturn(Optional.empty());
        assertThrows(AuthorizeException.class, () -> tokenService.refreshToken(USER_ID));
    }

    @Test
    void when_get_token_and_no_user_is_found_should_throw_exception() {
        when(userCredentialsService.get(USER_ID)).thenReturn(Optional.empty());
        assertThrows(AuthorizeException.class, () -> tokenService.getTokenForUser(USER_ID));
    }

    @Test
    void when_get_token_token_is_valid_should_return_token() {
        when(userCredentialsService.get(USER_ID)).thenReturn(Optional.of(userCredentials));
        when(userCredentials.getToken()).thenReturn(TOKEN);
        when(userCredentials.getExpireDate()).thenReturn(LocalDateTime.now().plusMinutes(10));

        String tokenForUser = tokenService.getTokenForUser(USER_ID);
        assertEquals(TOKEN, tokenForUser);
        verify(userCredentials).getToken();
    }

    //TODO: COMPLETE TEST
    @Test
    void when_get_token_token_is_not_valid_should_return_new_token() {
        when(userCredentialsService.get(USER_ID)).thenReturn(Optional.of(userCredentials));
        when(userCredentials.getExpireDate()).thenReturn(LocalDateTime.now().minusMinutes(10));
        when(userCredentials.getRefreshToken()).thenReturn(REFRESH_TOKEN);
        ResponseEntity responseEntity = new ResponseEntity(refreshToken, HttpStatus.OK);
        when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);
        when(refreshToken.getAccessToken()).thenReturn(NEW_TOKEN);
        when(refreshToken.getExpiresIn()).thenReturn(3600);
        when(userCredentialsService.save(any(UserCredentials.class))).thenReturn(userCredentials);

        String tokenForUser = tokenService.getTokenForUser(USER_ID);

        assertEquals(NEW_TOKEN, tokenForUser);
    }

    @Test
    void when_request_refresh_token_with_existing_user() throws AuthorizeException {
        when(userCredentialsService.get(USER_ID)).thenReturn(Optional.of(userCredentials));
        when(userCredentials.getRefreshToken()).thenReturn(REFRESH_TOKEN);
        ResponseEntity responseEntity = new ResponseEntity(refreshToken, HttpStatus.OK);
        when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);
        when(refreshToken.getAccessToken()).thenReturn(NEW_TOKEN);
        when(refreshToken.getRefreshToken()).thenReturn(REFRESH_TOKEN);
        when(refreshToken.getExpiresIn()).thenReturn(EXPIRES_IN);
        when(userCredentialsService.save(any(UserCredentials.class))).thenReturn(userCredentials);

        final Token tokenDTO = tokenService.refreshToken(USER_ID);

        assertEquals(REFRESH_TOKEN, tokenDTO.getRefreshToken());
        assertEquals(NEW_TOKEN, tokenDTO.getAccessToken());
        assertEquals(EXPIRES_IN, tokenDTO.getExpiresIn());
    }

}