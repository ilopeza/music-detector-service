package com.musicinfofinder.musicdetectorsrv.models.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class UserCredentialsDTO implements Serializable {
	@JsonProperty(value = "user_id")
	private String userId;
	private String token;
	@JsonProperty(value = "refresh_token")
	private String refreshToken;
	private List<String> scopes;
	@JsonProperty(value = "expire_date")
	private LocalDateTime expireDate;

	public UserCredentialsDTO() {
	}

	public static UserCredentialsDTO fromEntity(UserCredentials entity) {
		return UserCredentialsDTOBuilder.anUserCredentialsDTO()
						.withUserId(entity.getUserId())
						.withExpireDate(entity.getExpireDate())
						.withRefreshToken(entity.getRefreshToken())
						.withScopes(entity.getScopes())
						.withToken(entity.getToken())
						.build();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public List<String> getScopes() {
		return scopes;
	}

	public void setScopes(List<String> scopes) {
		this.scopes = scopes;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public static final class UserCredentialsDTOBuilder {
		private String userId;
		private String token;
		private String refreshToken;
		private List<String> scopes;
		private LocalDateTime expireDate;

		private UserCredentialsDTOBuilder() {
		}

		public static UserCredentialsDTOBuilder anUserCredentialsDTO() {
			return new UserCredentialsDTOBuilder();
		}

		public UserCredentialsDTOBuilder withUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public UserCredentialsDTOBuilder withToken(String token) {
			this.token = token;
			return this;
		}

		public UserCredentialsDTOBuilder withRefreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}

		public UserCredentialsDTOBuilder withScopes(List<String> scopes) {
			this.scopes = scopes;
			return this;
		}

		public UserCredentialsDTOBuilder withExpireDate(LocalDateTime expireDate) {
			this.expireDate = expireDate;
			return this;
		}

		public UserCredentialsDTO build() {
			UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
			userCredentialsDTO.setUserId(userId);
			userCredentialsDTO.setToken(token);
			userCredentialsDTO.setRefreshToken(refreshToken);
			userCredentialsDTO.setScopes(scopes);
			userCredentialsDTO.setExpireDate(expireDate);
			return userCredentialsDTO;
		}
	}
}
