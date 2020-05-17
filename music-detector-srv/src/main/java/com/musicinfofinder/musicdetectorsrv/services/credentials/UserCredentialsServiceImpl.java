package com.musicinfofinder.musicdetectorsrv.services.credentials;

import com.musicinfofinder.musicdetectorsrv.exceptions.InvalidParameterException;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserCredentialsDTO;
import com.musicinfofinder.musicdetectorsrv.repository.IUserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserCredentialsServiceImpl implements IUserCredentialsService {
	@Autowired
	IUserCredentialsRepository authenticationRepository;

	@Override
	public UserCredentialsDTO save(String userId, String code, TokenDTO token) {
		if (isBlank(userId)) {
			throw new InvalidParameterException("User id can not be null or blank");
		}
		if (isBlank(code)) {
			throw new InvalidParameterException("Code can not be null or blank");
		}
		if (isNull(token)) {
			throw new InvalidParameterException("Token information is missing.");
		}
		UserCredentials userCredentials = UserCredentials.AuthenticationBuilder.anAuthentication()
						.withApplicationCode(code)
						.withUserId(userId)
						.withExpiresIn(token.getExpiresIn())
						.withScopes(token.getScope())
						.withRefreshToken(token.getRefreshToken())
						.withToken(token.getAccessToken())
						.withTokenType(token.getTokenType())
						.build();
		UserCredentialsDTO userCredentialsDTO = UserCredentialsDTO.fromEntity(authenticationRepository.save(userCredentials));
		return userCredentialsDTO;
	}

	@Cacheable(value = "User_credentials", key = "#userId")
	@Override
	public Optional<UserCredentialsDTO> get(String userId) {
		final Optional<UserCredentials> optionalUserCredentials = authenticationRepository.findById(userId);
		if (!optionalUserCredentials.isPresent()) {
			return Optional.empty();
		}
		final UserCredentialsDTO userCredentialsDTO = UserCredentialsDTO.fromEntity(optionalUserCredentials.get());
		return Optional.of(userCredentialsDTO);
	}
}