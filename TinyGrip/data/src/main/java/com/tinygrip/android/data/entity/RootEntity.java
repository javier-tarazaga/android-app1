
package com.tinygrip.android.data.entity;

/**
 * Root Entity used in the data layer.
 */
public class RootEntity {

    private LinkEntity previewAreas;
    private LinkEntity areas;
    private AuthEntity auth;

    public LinkEntity getPreviewAreas() {
        return previewAreas;
    }

    public void setPreviewAreas(LinkEntity previewAreas) {
        this.previewAreas = previewAreas;
    }

    public LinkEntity getAreas() {
        return areas;
    }

    public void setAreas(LinkEntity areas) {
        this.areas = areas;
    }

    public AuthEntity getAuth() {
        return auth;
    }

    public void setAuth(AuthEntity auth) {
        this.auth = auth;
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
        private LinkEntity user;
        private LinkEntity token;
        private LinkEntity register;

        public LinkEntity getUser() {
            return user;
        }

        public void setUser(LinkEntity user) {
            this.user = user;
        }

        public LinkEntity getToken() {
            return token;
        }

        public void setToken(LinkEntity token) {
            this.token = token;
        }

        public LinkEntity getRegister() {
            return register;
        }

        public void setRegister(LinkEntity register) {
            this.register = register;
        }

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
