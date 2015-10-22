
package com.tinygrip.android.presentation.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents a user in the presentation layer.
 */
public class AreaModel {

    @Getter private final int userId;

    public AreaModel(int userId) {
        this.userId = userId;
    }

    @Getter @Setter private String coverUrl;

    @Getter @Setter private String fullName;

    @Getter @Setter private String email;

    @Getter @Setter private String description;

    @Getter @Setter private int followers;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Model Details *****\n");
        stringBuilder.append("id=" + this.getUserId() + "\n");
        stringBuilder.append("cover url=" + this.getCoverUrl() + "\n");
        stringBuilder.append("fullname=" + this.getFullName() + "\n");
        stringBuilder.append("email=" + this.getEmail() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("followers=" + this.getFollowers() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
