package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserCredentialsDTO;
import com.musicinfofinder.musicdetectorsrv.services.credentials.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/credentials")
public class UserCredentialsController {

	@Autowired
	private IUserCredentialsService userCredentialsService;

	@RequestMapping("/{user}")
	public Optional<UserCredentialsDTO> getUserCredentials(@PathVariable("user") String user) {
		return userCredentialsService.get(user);
	}
}
