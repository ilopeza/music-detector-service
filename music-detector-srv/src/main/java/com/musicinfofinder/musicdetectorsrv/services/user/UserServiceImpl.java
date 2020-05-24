package com.musicinfofinder.musicdetectorsrv.services.user;

import com.musicinfofinder.musicdetectorsrv.exceptions.InvalidParameterException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.exceptions.UserException;
import com.musicinfofinder.musicdetectorsrv.exceptions.UserNotFoundException;
import com.musicinfofinder.musicdetectorsrv.models.entities.user.User;
import com.musicinfofinder.musicdetectorsrv.models.request.user.UserRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.user.UserRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserDTO;
import com.musicinfofinder.musicdetectorsrv.repository.IUserRepository;
import com.musicinfofinder.musicdetectorsrv.services.authorization.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ITokenService tokenService;

    @Override
    public UserDTO requestCurrent(String userId) throws RestClientException, MalformedRequestException {
        String token = tokenService.getTokenForUser(userId);
        UserRequest userRequest = UserRequestBuilder.getRequestBuilder(token)
                .build();
        HttpEntity<Object> requestEntity = new HttpEntity<>(userRequest.getBody(), userRequest.getHeaders());
        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(userRequest.getUri(), HttpMethod.GET,
                requestEntity, UserDTO.class);
        final UserDTO user = responseEntity.getBody();

        return user;
    }

    @Override
    public User save(User user) throws UserException {
        if (isNull(user)) {
            throw new InvalidParameterException("User can not be null", HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> get(String id) throws UserException {
        if (isBlank(id)) {
            throw new InvalidParameterException("User can not be null", HttpStatus.BAD_REQUEST);
        }
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found"));
        return Optional.of(user);
    }
}
