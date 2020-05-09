package com.musicinfofinder.musicdetectorsrv.models.entities.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.enums.ObjectType;
import com.musicinfofinder.musicdetectorsrv.enums.ProductEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

/**
 * User response object
 * https://developer.spotify.com/documentation/web-api/reference/users-profile/get-current-users-profile/
 */
@RedisHash("User")
public class User implements Serializable {
	@Id
	private String id;
	@JsonProperty("display_name")
	private String displayName;
	private String country;
	private String email;
	private String href;
	private List<UserImage> images;
	private ProductEnum product;
	private ObjectType type;
	private String uri;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<UserImage> getImages() {
		return images;
	}

	public void setImages(List<UserImage> images) {
		this.images = images;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ProductEnum getProduct() {
		return product;
	}

	public void setProduct(ProductEnum product) {
		this.product = product;
	}

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}
}
