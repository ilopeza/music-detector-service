package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;
import com.musicinfofinder.musicdetectorsrv.models.response.TokenResponse;
import com.musicinfofinder.musicdetectorsrv.services.AuthorizationService;
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
	private AuthorizationService authorizationService;

	@RequestMapping("/authorize")
	public void authorize() {
		try {
			authorizationService.authorize();
		} catch (AuthorizeException exception) {
			logger.error("Cannot authorize", exception);
		}
	}

	@RequestMapping("/postAuthorize")
	public Optional<TokenResponse> postAuthorize(AuthorizeResponse authResponse) {
		try {
			final TokenResponse tokenResponse = authorizationService.getToken(authResponse.getCode());
			return Optional.of(tokenResponse);
		} catch (AuthorizeException exception) {
			logger.error("Cannot authorize", exception);
		}
		return Optional.empty();
	}

	@RequestMapping("/refreshtoken")
	public Optional<TokenResponse> refreshToken() {
		final TokenResponse tokenResponse;
		try {
			tokenResponse = authorizationService.refreshToken();
			return Optional.of(tokenResponse);
		} catch (AuthorizeException exception) {
			logger.error("Cannot authorize", exception);
		}
		return Optional.empty();
	}
}
