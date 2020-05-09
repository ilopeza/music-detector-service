package com.musicinfofinder.musicdetectorsrv.services;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;
import com.musicinfofinder.musicdetectorsrv.services.authorization.AuthorizationServiceImpl;
import com.musicinfofinder.musicdetectorsrv.services.authorization.IAuthorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

	IAuthorizationService authorizationService;
	@Mock
	AuthorizeResponse authorizeResponse;

	@BeforeEach
	void initMocks() {
		authorizationService = new AuthorizationServiceImpl();
	}
	@Test
	void when_user_does_not_authorize_postAuthorize_should_throw_exception() {
		when(authorizeResponse.hasError()).thenReturn(true);
		assertThrows(AuthorizeException.class, () ->
						authorizationService.postAuthorize(authorizeResponse));
	}
}