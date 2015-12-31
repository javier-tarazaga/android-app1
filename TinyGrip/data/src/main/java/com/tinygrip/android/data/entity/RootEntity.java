
package com.tinygrip.android.data.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Root Entity used in the data layer.
 */
public class RootEntity {

    @Getter @Setter private LinkEntity previewAreas;
    @Getter @Setter private LinkEntity areas;
    @Getter @Setter private AuthEntity auth;

    public RootEntity() {
        //empty
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Root Entity Details *****\n");
        stringBuilder.append("previewAreas=" + this.getPreviewAreas() + "\n");
        stringBuilder.append("areas=" + this.getAreas() + "\n");
        stringBuilder.append("auth=" + this.getAuth() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

    public static class AuthEntity {
        @Getter @Setter private LinkEntity user;
        @Getter @Setter private LinkEntity token;
        @Getter @Setter private LinkEntity register;

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("***** Auth Entity Details *****\n");
            stringBuilder.append("user=" + this.getUser() + "\n");
            stringBuilder.append("token=" + this.getToken() + "\n");
            stringBuilder.append("register=" + this.getRegister() + "\n");
            stringBuilder.append("*******************************");

            return stringBuilder.toString();
        }
    }
}
