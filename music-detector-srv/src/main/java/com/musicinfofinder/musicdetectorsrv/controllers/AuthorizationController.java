package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.Token;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserCredentialsDTO;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserDTO;
import com.musicinfofinder.musicdetectorsrv.services.authorization.IAuthorizationService;
import com.musicinfofinder.musicdetectorsrv.services.authorization.ITokenService;
import com.musicinfofinder.musicdetectorsrv.services.credentials.IUserCredentialsService;
import com.musicinfofinder.musicdetectorsrv.services.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class AuthorizationController {

    private final IAuthorizationService authorizationService;
    private final ITokenService tokenService;
    private final IUserCredentialsService userCredentialsService;
    private final IUserService userService;

    public AuthorizationController(IAuthorizationService authorizationService,
                                   ITokenService tokenService,
                                   IUserCredentialsService userCredentialsService,
                                   IUserService userService) {
        this.authorizationService = authorizationService;
        this.tokenService = tokenService;
        this.userCredentialsService = userCredentialsService;
        this.userService = userService;
    }

    @RequestMapping("/authorize")
    public void authorize() {
        authorizationService.authorize();
    }

    @RequestMapping("/postAuthorize")
    public Optional<UserCredentialsDTO> postAuthorize(AuthorizationDTO authResponse) {
        final Token token = tokenService.requestToken(authResponse.getCode());
        final UserDTO currentUser = userService.requestCurrent(token.getAccessToken());
        userService.save(currentUser.toEntity());
        final UserCredentials userCredentials = userCredentialsService
                .save(currentUser.getId(), authResponse.getCode(), token);
        return Optional.of(UserCredentialsDTO.fromEntity(userCredentials));
    }
}
