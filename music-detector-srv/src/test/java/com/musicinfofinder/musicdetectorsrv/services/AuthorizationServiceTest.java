package com.musicinfofinder.musicdetectorsrv.services;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.InvalidParameterException;
import com.musicinfofinder.musicdetectorsrv.models.entities.authentication.Authentication;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.token.TokenRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;
import com.musicinfofinder.musicdetectorsrv.repository.IAuthenticationRepository;
import com.musicinfofinder.musicdetectorsrv.services.authorization.AuthorizationServiceImpl;
import com.musicinfofinder.musicdetectorsrv.services.authorization.IAuthorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

	private static final String USER_ID = "user_id";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String NEW_TOKEN = "new_token";
	private static final int EXPIRES_IN = 3600;
	private static final String CLIENT_ID = "client_id";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String URI_REQUEST = "http://localhost:8080";
	IAuthorizationService authorizationService;
	@Mock
	AuthorizationDTO authorizationDTO;

	@Mock
	Authentication authentication;

	@Mock
	IAuthenticationRepository authenticationRepository;

	@Mock
	TokenRequestBuilder tokenRequestBuilder;

	@Mock
	TokenRequest tokenRequest;

	@Mock
	RestTemplate restTemplate;

	@BeforeEach
	void initMocks() {
		authorizationService = new AuthorizationServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void when_user_does_not_authorize_postAuthorize_should_throw_exception() {
		when(authorizationDTO.hasError()).thenReturn(true);
		assertThrows(AuthorizeException.class, () ->
						authorizationService.postAuthorize(authorizationDTO));
	}

	@Test
	void when_request_refresh_token_with_null_user_id_should_throw_exception() throws InvalidParameterException {
		assertThrows(InvalidParameterException.class, () -> authorizationService.refreshToken(null));
	}

	@Test
	void when_request_refresh_token_with_empty_user_id_should_throw_exception() throws InvalidParameterException {
		assertThrows(InvalidParameterException.class, () -> authorizationService.refreshToken(""));
	}

	//TODO: THROWING NPE WHEN MOCKING THE REPOSITORY
	void when_request_refresh_token_with_non_existing_user_should_throw_exception() throws AuthorizeException {
		when(authenticationRepository.findById(USER_ID)).thenReturn(Optional.empty());
		assertThrows(AuthorizeException.class, () -> authorizationService.refreshToken(USER_ID));
	}

	//TODO: COMPLETE HAPPY PATH TEST
	/**
	 @Test void when_request_refresh_token_with_existing_user() throws AuthorizeException {
	 when(authenticationRepository.findById(USER_ID)).thenReturn(Optional.of(authentication));
	 when(authentication.getRefreshToken()).thenReturn(REFRESH_TOKEN);
	 when(tokenRequestBuilder.build()).thenReturn(tokenRequest);
	 when(tokenRequest.getUri()).thenReturn(URI.create(URI_REQUEST));
	 when(tokenRequest.getBody()).thenReturn()
	 ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
	 HttpEntity<Object> requestEntity = new HttpEntity<>(tokenRequest.getBody());
	 when(restTemplate.exchange(tokenRequest.getUri(), HttpMethod.POST, requestEntity, TokenDTO.class))
	 .thenReturn();

	 final TokenDTO tokenDTO = authorizationService.refreshToken(USER_ID);
	 assertEquals(REFRESH_TOKEN, tokenDTO.getRefreshToken());
	 assertEquals(NEW_TOKEN, tokenDTO.getAccessToken());
	 assertEquals(EXPIRES_IN, tokenDTO.getExpiresIn());
	 }**/
}