package com.musicinfofinder.musicdetectorsrv.services.user;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.exceptions.UserException;
import com.musicinfofinder.musicdetectorsrv.models.entities.user.User;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

public interface IUserService {
	User getCurrentUser(String token) throws RestClientException, MalformedRequestException;

	User save(User user) throws UserException;

	Optional<User> getUserById(String id) throws UserException;
}
