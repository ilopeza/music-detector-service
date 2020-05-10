package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;
import com.musicinfofinder.musicdetectorsrv.models.response.TokenResponse;
import com.musicinfofinder.musicdetectorsrv.services.authorization.IAuthorizationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorizationController {
	private final static Logger logger = LogManager.getLogger(AuthorizationController.class);

	@Autowired
	private IAuthorizationService authorizationService;

	@RequestMapping("/authorize")
	public void authorize() {
		authorizationService.authorize();
	}

	@RequestMapping("/postAuthorize")
	public Optional<TokenResponse> postAuthorize(AuthorizeResponse authResponse) {
		final TokenResponse tokenResponse = authorizationService.getToken(authResponse.getCode());
		return Optional.of(tokenResponse);
	}

	@RequestMapping("/refresh-token")
	public Optional<TokenResponse> refreshToken() {
		//TODO: CHANGE REFRESH TOKEN WITH NULL. THIS IS JUST TEMPORAL UNTIL WE CAN STORE THE DATE IN REDIS.
		final TokenResponse tokenResponse = authorizationService.refreshToken();
		return Optional.of(tokenResponse);
	}
}
