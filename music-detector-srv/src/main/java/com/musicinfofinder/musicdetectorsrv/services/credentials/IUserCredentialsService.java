package com.musicinfofinder.musicdetectorsrv.services.credentials;

import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserCredentialsDTO;

import java.util.Optional;

public interface IUserCredentialsService {
	/**
	 * Saves the user's credentials in the database. The user is determined by the userId. The credentials take the info
	 * from the token (expire date, scope, refresh_token) and the code of the application for the user.
	 *
	 * @param userId Id of the user
	 * @param code   Application code for the user, which is retrieved after the authorization flow finishes.
	 * @param token  TokenDTO built after calling /token api
	 * @return credentials of the user.
	 * @throws IllegalArgumentException if any of the params is empty
	 */
	UserCredentialsDTO save(String userId, String code, TokenDTO token);

	UserCredentialsDTO save(UserCredentials userCredentials);

	/**
	 * Retrieves the credentials for the user specified in the params
	 *
	 * @param userId Id of the user
	 * @return Credentials of the user if present. Otherwise will return empty.
	 */
	Optional<UserCredentials> get(String userId);
}
