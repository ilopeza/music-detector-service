package com.musicinfofinder.musicdetectorsrv.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("api")
public class SpotifyCredentials {
    private String clientId;
    private String clientSecret;
}
