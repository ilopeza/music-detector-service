package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.entities.authentication.Authentication;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserDTO;
import com.musicinfofinder.musicdetectorsrv.services.authorization.IAuthorizationService;
import com.musicinfofinder.musicdetectorsrv.services.user.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorizationController {
	private final static Logger logger = LogManager.getLogger(AuthorizationController.class);

	@Autowired
	private IAuthorizationService authorizationService;
	@Autowired
	private IUserService userService;

	@RequestMapping("/authorize")
	public void authorize() {
		authorizationService.authorize();
	}

	@RequestMapping("/postAuthorize")
	public Optional<Authentication> postAuthorize(AuthorizationDTO authResponse) {
		final TokenDTO tokenDTO = authorizationService.requestToken(authResponse.getCode());
		final UserDTO currentUser = userService.getCurrentUser(tokenDTO.getAccessToken());

		final Authentication authentication = authorizationService
						.save(currentUser.getId(), authResponse.getCode(), tokenDTO);

		return Optional.of(authentication);
	}

	@RequestMapping("/authentication/{user}")
	public Optional<Authentication> getAuthenticationForUser(@PathVariable("user") String user) {
		final Optional<Authentication> authentication = authorizationService.getAuthenticationByUserId(user);
		return authentication;
	}

	@RequestMapping("/refresh-token/{user}")
	public Optional<TokenDTO> refreshToken(@PathVariable("user") String user) {
		final TokenDTO tokenDTO = authorizationService.refreshToken(user);
		return Optional.of(tokenDTO);
	}
}
