package com.musicinfofinder.musicdetectorsrv.models.entities.credentials;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.util.StringUtils.commaDelimitedListToSet;

/**
 * Entity to store the credentials information for one user.
 */
@RedisHash("User_credentials")
public class UserCredentials implements Serializable {

    @Id
    private String userId;
    private String applicationCode;
    private String token;
    private String refreshToken;
    private int expiresIn;
    private String tokenType;
    private List<String> scopes;
    private LocalDateTime expireDate;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void refreshToken(String token, int expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.expireDate = LocalDateTime.now()
                .plus(Duration.ofSeconds(expiresIn));
    }

    public String getUserId() {
        return this.userId;
    }

    public String getToken() {
        return token;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public static final class AuthenticationBuilder {
        private String userId;
        private String applicationCode;
        private String token;
        private String refreshToken;
        private int expiresIn;
        private String tokenType;
        private List<String> scopes;
        private LocalDateTime expireDate;

        private AuthenticationBuilder() {
        }

        public static AuthenticationBuilder anAuthentication() {
            return new AuthenticationBuilder();
        }

        public AuthenticationBuilder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public AuthenticationBuilder withApplicationCode(String applicationCode) {
            this.applicationCode = applicationCode;
            return this;
        }

        public AuthenticationBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationBuilder withRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public AuthenticationBuilder withExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
            this.expireDate = LocalDateTime.now()
                    .plus(Duration.ofSeconds(expiresIn));
            return this;
        }

        public AuthenticationBuilder withTokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public AuthenticationBuilder withScopes(String scopes) {
            final Set<String> scopesSet = commaDelimitedListToSet(scopes);
            this.scopes = new ArrayList<>(scopesSet);
            return this;
        }

        public UserCredentials build() {
            UserCredentials userCredentials = new UserCredentials();
            userCredentials.tokenType = this.tokenType;
            userCredentials.expireDate = this.expireDate;
            userCredentials.token = this.token;
            userCredentials.applicationCode = this.applicationCode;
            userCredentials.refreshToken = this.refreshToken;
            userCredentials.expiresIn = this.expiresIn;
            userCredentials.userId = this.userId;
            userCredentials.scopes = this.scopes;
            return userCredentials;
        }
    }
}
