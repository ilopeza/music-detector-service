package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.config.SpotifyCredentials;
import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizationSpotifyRestApiException;
import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.InvalidParameterException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.Token;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.models.request.token.RefreshTokenRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.services.credentials.IUserCredentialsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class TokenServiceImpl implements ITokenService {

    private static final String REDIRECT_URI = "http://localhost:8081/postAuthorize";
    private final IUserCredentialsService userCredentialsService;
    private final RestTemplate restTemplate;
    private final SpotifyCredentials spotifyCredentials;

    public TokenServiceImpl(IUserCredentialsService userCredentialsService, RestTemplate restTemplate, SpotifyCredentials spotifyCredentials) {
        this.userCredentialsService = userCredentialsService;
        this.restTemplate = restTemplate;
        this.spotifyCredentials = spotifyCredentials;
    }

    @Override
    public Token requestToken(String code) throws AuthorizeException, MalformedRequestException {
        if (isBlank(code)) {
            throw new InvalidParameterException("The application code is required to get the token.", HttpStatus.BAD_REQUEST);
        }
        final TokenRequest tokenRequest = TokenRequestBuilder.requestBuilder(spotifyCredentials.getClientId(),
                spotifyCredentials.getClientSecret())
                .withCode(code)
                .withRedirectUri(REDIRECT_URI)
                .build();
        HttpEntity<Object> requestEntity = new HttpEntity<>(tokenRequest.getBody(), tokenRequest.getHeaders());
        ResponseEntity<TokenDTO> responseEntity;
        try {
            responseEntity = restTemplate.exchange(tokenRequest.getUri(), HttpMethod.POST, requestEntity, TokenDTO.class);
        } catch (RestClientException exception) {
            throw new AuthorizationSpotifyRestApiException(exception);
        }
        return new Token(responseEntity.getBody());
    }

    @Override
    public Token refreshToken(String userId) {
        if (isBlank(userId)) {
            throw new InvalidParameterException("User id is required to refresh the token.", HttpStatus.BAD_REQUEST);
        }
        //get credentials from db
        UserCredentials userCredentials = userCredentialsService.get(userId)
                .orElseThrow(() -> new AuthorizeException("Could not get credentials for user with id " + userId,
                        HttpStatus.UNAUTHORIZED));
        //call spotify api to refresh token
        final Token refreshedToken = requestRefreshToken(userCredentials.getRefreshToken());
        //set new token and expire date
        updateCurrentUserCredentials(userCredentials, refreshedToken);
        return refreshedToken;
    }

    /**
     * Updates the credentials for the user with the new expire date and token.
     *
     * @param userCredentials Credentials of the user to update
     * @param refreshedToken Token
     */
    private void updateCurrentUserCredentials(UserCredentials userCredentials, Token refreshedToken) {
        userCredentials.refreshToken(refreshedToken.getAccessToken(), refreshedToken.getExpiresIn());
        //update the values in the db
        userCredentialsService.save(userCredentials);
    }

    @Override
    public Token requestRefreshToken(String refreshToken) throws AuthorizeException, MalformedRequestException {
        final TokenRequest tokenRequest = RefreshTokenRequestBuilder.requestBuilder(spotifyCredentials.getClientId(),
                spotifyCredentials.getClientSecret())
                .withRefreshToken(refreshToken)
                .build();

        HttpEntity<Object> requestEntity = new HttpEntity<>(tokenRequest.getBody(), tokenRequest.getHeaders());
        ResponseEntity<TokenDTO> responseEntity;
        try {
            responseEntity = restTemplate.exchange(tokenRequest.getUri(), HttpMethod.POST,
                    requestEntity, TokenDTO.class);
        } catch (RestClientException exception) {
            throw new AuthorizationSpotifyRestApiException(exception);
        }
        return new Token(responseEntity.getBody());
    }

    @Override
    public String getTokenForUser(String userId) {
        UserCredentials userCredentials = userCredentialsService.get(userId)
                .orElseThrow(() -> new AuthorizeException("Could not get credentials for user with id " + userId,
                        HttpStatus.UNAUTHORIZED));
        if (isTokenValid(userCredentials)) {
            return userCredentials.getToken();
        }
        Token token = refreshToken(userId);
        return token.getAccessToken();
    }

    private boolean isTokenValid(UserCredentials userCredentials) {
        LocalDateTime expireDate = userCredentials
                .getExpireDate();
        return LocalDateTime.now().isBefore(expireDate);
    }
}
