package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserDTO;
import com.musicinfofinder.musicdetectorsrv.services.credentials.IUserCredentialsService;
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
@RequestMapping("/user")
public class UserController {
	private final static Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	IUserService userService;
	@Autowired
	IUserCredentialsService userCredentialsService;

	@RequestMapping("/current")
	public Optional<UserDTO> getCurrentUser() {
		String token = "BQC37G0IVcECDN7c8vBKDspdmawoF_8z4mQ9XLxFQ2-9JYm4dm9b8CnWO0kQ6Wmd_Hx4khyUbjsolHmWf9qDdpZxAgN58a5qUOujINBknDMJIb0vpPGftWcztPzKL9cnjKnV9XEU6cZXkOchoj2HKOBOFL8f0NMd";
		final UserDTO currentUser = userService.requestCurrent(token);
		if (isNull(currentUser)) {
			logger.error("Could not get information for current user with token {}", token);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not get the information for the current user");
		}
		userService.save(currentUser.toEntity());
		return Optional.of(currentUser);
	}

	@GetMapping("/{userId}")
	public Optional<UserDTO> findUserById(@PathVariable("userId") String userId) {
		return userService.get(userId);
	}
}
