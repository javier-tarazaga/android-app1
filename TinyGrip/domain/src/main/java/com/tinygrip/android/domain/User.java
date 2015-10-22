
package com.tinygrip.android.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents a User in the domain layer.
 */
public class User {

    @Getter private final String id;

    public User(String id) {
        this.id = id;
    }

    @Getter @Setter private String coverUrl;

    @Getter @Setter private String avatarUrl;

    @Getter @Setter private String firstName;

    @Getter @Setter private String lastName;

    @Getter @Setter private String email;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Details *****\n");
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
