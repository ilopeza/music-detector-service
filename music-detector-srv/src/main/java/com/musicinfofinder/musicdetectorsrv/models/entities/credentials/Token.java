package com.musicinfofinder.musicdetectorsrv.models.entities.credentials;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.TokenDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Token {
    private String accessToken;
    private String tokenType;
    private String scope;
    private int expiresIn;
    private String refreshToken;

    public Token(TokenDTO dto) {
        this.accessToken = dto.getAccessToken();
        this.tokenType = dto.getTokenType();
        this.scope = dto.getScope();
        this.expiresIn = dto.getExpiresIn();
        this.refreshToken = dto.getRefreshToken();
    }
}
