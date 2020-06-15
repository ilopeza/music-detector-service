package com.musicinfofinder.musicdetectorsrv.models.entities.user;

import com.musicinfofinder.musicdetectorsrv.enums.ObjectType;
import com.musicinfofinder.musicdetectorsrv.enums.ProductEnum;
import com.musicinfofinder.musicdetectorsrv.models.response.commons.Image;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

/**
 * User entity
 */
@RedisHash("User")
@Data
public class User implements Serializable {
    @Id
    private String id;
    private String displayName;
    private String country;
    private String email;
    private String href;
    private List<Image> images;
    private ProductEnum product;
    private ObjectType type;
    private String uri;

    public static final class UserBuilder {
        private String id;
        private String displayName;
        private String country;
        private String email;
        private String href;
        private List<Image> images;
        private ProductEnum product;
        private ObjectType type;
        private String uri;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public UserBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withHref(String href) {
            this.href = href;
            return this;
        }

        public UserBuilder withImages(List<Image> images) {
            this.images = images;
            return this;
        }

        public UserBuilder withProduct(ProductEnum product) {
            this.product = product;
            return this;
        }

        public UserBuilder withType(ObjectType type) {
            this.type = type;
            return this;
        }

        public UserBuilder withUri(String uri) {
            this.uri = uri;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setDisplayName(displayName);
            user.setCountry(country);
            user.setEmail(email);
            user.setHref(href);
            user.setImages(images);
            user.setProduct(product);
            user.setType(type);
            user.setUri(uri);
            return user;
        }
    }
}
