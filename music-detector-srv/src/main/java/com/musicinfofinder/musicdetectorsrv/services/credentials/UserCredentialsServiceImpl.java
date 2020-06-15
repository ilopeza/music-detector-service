package com.musicinfofinder.musicdetectorsrv.services.credentials;

import com.musicinfofinder.musicdetectorsrv.exceptions.InvalidParameterException;
import com.musicinfofinder.musicdetectorsrv.exceptions.UserNotFoundException;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.Token;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.repository.IUserCredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
@Slf4j
public class UserCredentialsServiceImpl implements IUserCredentialsService {
    @Autowired
    IUserCredentialsRepository userCredentialsRepository;

    @Override
    public UserCredentials save(String userId, String code, Token token) {
        log.debug("Starting call to save token with userId {} and code {} and token {}", userId, code, token);
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
        return userCredentialsRepository.save(userCredentials);
    }

    @Override
    public UserCredentials save(UserCredentials userCredentials) {
        log.debug("Starting call to save user credentials  with {}", userCredentials);
        if (isNull(userCredentials)) {
            throw new InvalidParameterException("Credentials can not be null", HttpStatus.BAD_REQUEST);
        }
        return userCredentialsRepository.save(userCredentials);
    }

    @Cacheable(value = "User_credentials", key = "#userId")
    @Override
    public Optional<UserCredentials> get(String userId) {
        log.debug("Starting call to cacheable method get with userId {}", userId);
        UserCredentials userCredentials = userCredentialsRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " could not be found."));
        log.debug("Finishing call to cacheable methor get with userId {}", userId);
        return Optional.of(userCredentials);
    }
}
