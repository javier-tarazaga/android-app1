
package com.tinygrip.android.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents a User in the domain layer.
 */
public class PreviewArea {

    @Getter private final int userId;

    public PreviewArea(int userId) {
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

        stringBuilder.append("***** User Details *****\n");
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
