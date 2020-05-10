package com.musicinfofinder.musicdetectorsrv.services.user;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.exceptions.UserException;
import com.musicinfofinder.musicdetectorsrv.models.entities.user.User;
import com.musicinfofinder.musicdetectorsrv.models.request.user.UserRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.user.UserRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserDTO;
import com.musicinfofinder.musicdetectorsrv.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserService implements IUserService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	IUserRepository userRepository;

	@Override
	public UserDTO getCurrentUser(String token) throws RestClientException, MalformedRequestException {
		//TODO: SHOOULD GET THE TOKEN
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
			throw new UserException("User can not be null");
		}
		final User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public Optional<User> getUserById(String id) throws UserException {
		if (isBlank(id)) {
			throw new UserException("Id cannot be blank");
		}
		final Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser;
	}
}
