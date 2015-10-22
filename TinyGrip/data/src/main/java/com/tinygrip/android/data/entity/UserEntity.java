
package com.tinygrip.android.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * User Entity used in the data layer.
 */
public class UserEntity {

    @JsonProperty("Id") @Getter @Setter private String id;

    @JsonProperty("CoverUrl") @Getter @Setter private String coverUrl;

    @JsonProperty("AvatarUrl") @Getter @Setter private String avatarUrl;

    @JsonProperty("FirstName") @Getter @Setter private String firstName;

    @JsonProperty("LastName") @Getter @Setter private String lastName;

    @JsonProperty("Email") @Getter @Setter private String email;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
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
