package com.tinygrip.android.data.entity.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinygrip.android.data.entity.LinkEntity;

/**
 * Entity representing a user in the backend. Bear in mind that depending on the auth state, this entity will have
 * different properties set.
 */
public class UserEntity {

    private final LinkEntity login;
    private final LinkEntity register;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("CoverUrl")
    private String coverUrl;


    @JsonProperty("AvatarUrl")
    private String avatarUrl;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Email")
    private String email;

    public LinkEntity getLogin() {
        return login;
    }

    public LinkEntity getRegister() {
        return register;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonCreator
    public UserEntity(@JsonProperty("Login") LinkEntity login,
                      @JsonProperty("Register") LinkEntity register) {
        this.login = login;
        this.register = register;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("login=" + this.getLogin() + "\n");
        stringBuilder.append("register=" + this.getRegister() + "\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("avatar url=" + this.getAvatarUrl() + "\n");
        stringBuilder.append("cover url=" + this.getCoverUrl() + "\n");
        stringBuilder.append("first name=" + this.getFirstName() + "\n");
        stringBuilder.append("last name=" + this.getLastName() + "\n");
        stringBuilder.append("email=" + this.getEmail() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
