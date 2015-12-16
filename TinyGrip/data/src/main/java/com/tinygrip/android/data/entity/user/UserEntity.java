package com.tinygrip.android.data.entity.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinygrip.android.data.entity.LinkEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a user in the backend. Bear in mind that depending on the auth state, this entity will have
 * different properties set.
 */
public class UserEntity {

    @Getter private final LinkEntity login;
    @Getter private final LinkEntity register;

    @JsonProperty("Id") @Getter @Setter private String id;
    @JsonProperty("CoverUrl") @Getter @Setter private String coverUrl;
    @JsonProperty("AvatarUrl") @Getter @Setter private String avatarUrl;
    @JsonProperty("FirstName") @Getter @Setter private String firstName;
    @JsonProperty("LastName") @Getter @Setter private String lastName;
    @JsonProperty("Email") @Getter @Setter private String email;

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
