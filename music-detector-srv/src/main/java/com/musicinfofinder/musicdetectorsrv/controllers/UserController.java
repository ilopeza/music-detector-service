package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.exceptions.SpotifyWebAPIException;
import com.musicinfofinder.musicdetectorsrv.exceptions.UserException;
import com.musicinfofinder.musicdetectorsrv.models.entities.user.User;
import com.musicinfofinder.musicdetectorsrv.models.response.TokenResponse;
import com.musicinfofinder.musicdetectorsrv.services.authorization.IAuthorizationService;
import com.musicinfofinder.musicdetectorsrv.services.user.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final static Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	@RequestMapping("/current-user")
	public Optional<User> getCurrentUser() {
		try {
			String token = "BQC37G0IVcECDN7c8vBKDspdmawoF_8z4mQ9XLxFQ2-9JYm4dm9b8CnWO0kQ6Wmd_Hx4khyUbjsolHmWf9qDdpZxAgN58a5qUOujINBknDMJIb0vpPGftWcztPzKL9cnjKnV9XEU6cZXkOchoj2HKOBOFL8f0NMd";
			final User currentUser = userService.getCurrentUser(token);
			if (isNull(currentUser)) {
				throw new SpotifyWebAPIException(HttpStatus.BAD_REQUEST, "Could not get the information for the current user");
			}
			final User savedUser = userService.save(currentUser);
			return Optional.of(savedUser);
		} catch (MalformedRequestException exception) {
			logger.error("Cannot getCurrentUser", exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request", exception);
		} catch (SpotifyWebAPIException exception) {
			logger.error("Cannot getCurrentUser", exception);
			throw new ResponseStatusException(exception.getHttpStatus(), exception.getMessage(), exception);
		} catch (UserException exception) {
			logger.error("Cannot getCurrentUser", exception);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot save user", exception);
		}
	}

	@GetMapping("/{userId}")
	public Optional<User> findUserById(@PathVariable("userId") String userId) {
		try {
			final Optional<User> userById = userService.getUserById(userId);
			return userById;
		} catch (UserException exception) {
			logger.error("User with id {} could not be found.", userId);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request", exception);
		}
	}
}
