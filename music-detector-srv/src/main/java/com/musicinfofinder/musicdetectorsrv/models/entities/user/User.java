package com.musicinfofinder.musicdetectorsrv.models.entities.user;

import com.musicinfofinder.musicdetectorsrv.enums.ObjectType;
import com.musicinfofinder.musicdetectorsrv.enums.ProductEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

/**
 * User entity
 */
@RedisHash("User")
public class User implements Serializable {
    @Id
    private String id;
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

    public static final class UserBuilder {
        private String id;
        private String displayName;
        private String country;
        private String email;
        private String href;
        private List<UserImage> images;
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

        public UserBuilder withImages(List<UserImage> images) {
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
