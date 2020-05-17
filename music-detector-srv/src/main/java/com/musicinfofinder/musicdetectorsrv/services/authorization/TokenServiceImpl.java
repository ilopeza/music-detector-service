package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.InvalidParameterException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.models.request.token.RefreshTokenRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.repository.IUserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class TokenServiceImpl implements ITokenService {

	private final static String REDIRECT_URI = "http://localhost:8081/postAuthorize";
	@Autowired
	private IUserCredentialsRepository userCredentialsRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Value("${api.spotify.client.id}")
	private String clientId;
	@Value("${api.spotify.client.secret}")
	private String secretClient;

	@Override
	public TokenDTO requestToken(String code) throws AuthorizeException, MalformedRequestException {
		final TokenRequest tokenRequest = TokenRequestBuilder.requestBuilder(clientId, secretClient)
						.withCode(code)
						.withRedirectUri(REDIRECT_URI)
						.build();
		HttpEntity<Object> requestEntity = new HttpEntity<>(tokenRequest.getBody(), tokenRequest.getHeaders());
		ResponseEntity<TokenDTO> responseEntity;
		try {
			responseEntity = restTemplate.exchange(tokenRequest.getUri(), HttpMethod.POST, requestEntity, TokenDTO.class);
		} catch (RestClientException exception) {
			throw new AuthorizeException(exception);
		}
		return responseEntity.getBody();
	}

	@Override
	public TokenDTO refreshToken(String userId) {
		if (isBlank(userId)) {
			throw new InvalidParameterException("User id is required to refresh the token.");
		}
		//get credentials from db
		final Optional<UserCredentials> optionalUserCredentials = userCredentialsRepository.findById(userId);
		if (!optionalUserCredentials.isPresent()) {
			throw new InvalidParameterException("User with id " + userId + " is not registered. The user should register first.");
		}
		final UserCredentials userCredentials = optionalUserCredentials.get();
		//call spotify api to refresh token
		final TokenDTO refreshedToken = requestRefreshToken(userCredentials.getRefreshToken());
		//set new token and expire date
		updateCurrentUserCredentials(userCredentials, refreshedToken);
		return refreshedToken;
	}

	/**
	 * Updates the credentials for the user with the new expire date and token.
	 *
	 * @param userCredentials
	 * @param refreshedToken
	 */
	private void updateCurrentUserCredentials(UserCredentials userCredentials, TokenDTO refreshedToken) {
		userCredentials.refreshToken(refreshedToken.getAccessToken(), refreshedToken.getExpiresIn());
		//update the values in the db
		userCredentialsRepository.save(userCredentials);
	}

	@Override
	public TokenDTO requestRefreshToken(String refreshToken) throws AuthorizeException, MalformedRequestException {
		final TokenRequest tokenRequest = RefreshTokenRequestBuilder.requestBuilder(clientId, secretClient)
						.withRefreshToken(refreshToken)
						.build();

		HttpEntity<Object> requestEntity = new HttpEntity<>(tokenRequest.getBody(), tokenRequest.getHeaders());
		ResponseEntity<TokenDTO> responseEntity;
		try {
			responseEntity = restTemplate.exchange(tokenRequest.getUri(), HttpMethod.POST,
							requestEntity, TokenDTO.class);
		} catch (RestClientException exception) {
			throw new AuthorizeException(exception);
		}
		return responseEntity.getBody();
	}
}
