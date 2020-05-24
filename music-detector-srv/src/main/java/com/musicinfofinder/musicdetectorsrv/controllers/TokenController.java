package com.musicinfofinder.musicdetectorsrv.controllers;

import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.Token;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import com.musicinfofinder.musicdetectorsrv.services.authorization.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    public ITokenService tokenService;

    @RequestMapping("/refresh/{user}")
    public Optional<TokenDTO> refreshToken(@PathVariable("user") String user) {
        final Token token = tokenService.refreshToken(user);
        return Optional.of(new TokenDTO(token));
    }
}
