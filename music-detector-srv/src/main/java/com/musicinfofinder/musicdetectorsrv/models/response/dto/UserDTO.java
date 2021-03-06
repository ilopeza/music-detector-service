package com.musicinfofinder.musicdetectorsrv.models.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicinfofinder.musicdetectorsrv.enums.ObjectType;
import com.musicinfofinder.musicdetectorsrv.enums.ProductEnum;
import com.musicinfofinder.musicdetectorsrv.models.entities.user.User;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Image;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * User response object.
 *
 * @see :https://developer.spotify.com/documentation/web-api/reference/users-profile/get-current-users-profile/
 */
@Data
public class UserDTO {
    @Id
    private String id;
    @JsonProperty("display_name")
    private String displayName;
    private String country;
    private String email;
    private String href;
    private List<Image> images;
    private ProductEnum product;
    private ObjectType type;
    private String uri;

    public User toEntity() {
        return User.UserBuilder.anUser()
                .withCountry(this.country)
                .withDisplayName(this.displayName)
                .withEmail(this.email)
                .withHref(this.href)
                .withId(this.id)
                .withImages(this.images)
                .withProduct(this.product)
                .withType(this.type)
                .withUri(this.uri)
                .build();
    }

    public static final class UserDTOBuilder {
        private String id;
        private String displayName;
        private String country;
        private String email;
        private String href;
        private List<Image> images;
        private ProductEnum product;
        private ObjectType type;
        private String uri;

        private UserDTOBuilder() {
        }

        public static UserDTOBuilder anUserDTO() {
            return new UserDTOBuilder();
        }

        public UserDTOBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserDTOBuilder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public UserDTOBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public UserDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder withHref(String href) {
            this.href = href;
            return this;
        }

        public UserDTOBuilder withImages(List<Image> images) {
            this.images = images;
            return this;
        }

        public UserDTOBuilder withProduct(ProductEnum product) {
            this.product = product;
            return this;
        }

        public UserDTOBuilder withType(ObjectType type) {
            this.type = type;
            return this;
        }

        public UserDTOBuilder withUri(String uri) {
            this.uri = uri;
            return this;
        }

        public UserDTO fromEntity(User user) {
            return UserDTOBuilder.anUserDTO()
                    .withCountry(user.getCountry())
                    .withDisplayName(user.getDisplayName())
                    .withEmail(user.getEmail())
                    .withHref(user.getHref())
                    .withId(user.getId())
                    .withImages(user.getImages())
                    .withProduct(user.getProduct())
                    .withType(user.getType())
                    .withUri(user.getUri())
                    .build();
        }

        public UserDTO build() {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(id);
            userDTO.setDisplayName(displayName);
            userDTO.setCountry(country);
            userDTO.setEmail(email);
            userDTO.setHref(href);
            userDTO.setImages(images);
            userDTO.setProduct(product);
            userDTO.setType(type);
            userDTO.setUri(uri);
            return userDTO;
        }
    }
}
