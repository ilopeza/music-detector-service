package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserCredentialsDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserDTO;
import com.musicinfofinder.musicdetectorsrv.services.authorization.IAuthorizationService;
import com.musicinfofinder.musicdetectorsrv.services.authorization.ITokenService;
import com.musicinfofinder.musicdetectorsrv.services.credentials.IUserCredentialsService;
import com.musicinfofinder.musicdetectorsrv.services.user.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorizationController {
    private final static Logger logger = LogManager.getLogger(AuthorizationController.class);

    @Autowired
    private IAuthorizationService authorizationService;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private IUserCredentialsService userCredentialsService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/authorize")
    public void authorize() {
        authorizationService.authorize();
    }

    @RequestMapping("/postAuthorize")
    public Optional<UserCredentialsDTO> postAuthorize(AuthorizationDTO authResponse) {
        final TokenDTO tokenDTO = tokenService.requestToken(authResponse.getCode());
        final UserDTO currentUser = userService.requestCurrent(tokenDTO.getAccessToken());
        userService.save(currentUser.toEntity());
        final UserCredentialsDTO userCredentials = userCredentialsService
                .save(currentUser.getId(), authResponse.getCode(), tokenDTO);

        return Optional.of(userCredentials);
    }
}
