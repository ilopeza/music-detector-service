package com.musicinfofinder.musicdetectorsrv.services.credentials;

import com.musicinfofinder.musicdetectorsrv.exceptions.InvalidParameterException;
import com.musicinfofinder.musicdetectorsrv.exceptions.UserNotFoundException;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserCredentialsDTO;
import com.musicinfofinder.musicdetectorsrv.repository.IUserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserCredentialsServiceImpl implements IUserCredentialsService {
    @Autowired
    IUserCredentialsRepository userCredentialsRepository;

    @Override
    public UserCredentialsDTO save(String userId, String code, TokenDTO token) {
        if (isBlank(userId)) {
            throw new InvalidParameterException("User id can not be null or blank", HttpStatus.BAD_REQUEST);
        }
        if (isBlank(code)) {
            throw new InvalidParameterException("Code can not be null or blank", HttpStatus.BAD_REQUEST);
        }
        if (isNull(token)) {
            throw new InvalidParameterException("Token information is missing.", HttpStatus.BAD_REQUEST);
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
        //TODO: Move DTO's to controller.
        return UserCredentialsDTO.fromEntity(userCredentialsRepository.save(userCredentials));
    }

    @Override
    public UserCredentialsDTO save(UserCredentials userCredentials) {
        if (isNull(userCredentials)) {
            throw new InvalidParameterException("Credentials can not be null", HttpStatus.BAD_REQUEST);
        }
        return UserCredentialsDTO.fromEntity(userCredentialsRepository.save(userCredentials));
    }

    @Cacheable(value = "User_credentials", key = "#userId")
    @Override
    public Optional<UserCredentials> get(String userId) {
        UserCredentials userCredentials = userCredentialsRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " could not be found."));
        return Optional.of(userCredentials);
    }
}
