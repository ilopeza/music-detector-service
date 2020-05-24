package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.entities.user.User;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserDTO;
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

    @GetMapping("/current/{userId}")
    public Optional<UserDTO> getCurrentUser(@PathVariable("userId") String userId) {
        final UserDTO currentUser = userService.requestCurrent(userId);
        if (isNull(currentUser)) {
            logger.error("Could not get information for current user with id {}", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not get the information for the current user");
        }
        userService.save(currentUser.toEntity());
        return Optional.of(currentUser);
    }

    @GetMapping("/{userId}")
    public Optional<UserDTO> findUserById(@PathVariable("userId") String userId) {
        Optional<User> user = userService.get(userId);
        UserDTO userDTO = UserDTO.UserDTOBuilder.anUserDTO()
                .fromEntity(user.get());
        return Optional.of(userDTO);
    }
}
