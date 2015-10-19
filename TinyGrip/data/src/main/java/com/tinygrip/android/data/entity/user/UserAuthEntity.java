package com.tinygrip.android.data.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User Auth Entity used in the data layer. This will be the object returned by the backend when performing an authentication
 * against it in order to generate a new user Token to be used throughout the application.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthEntity {
    private String accessToken;
    private int expiresIn;
    private String userName;

    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUserName() {
        return userName;
    }

    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Auth Entity *****\n");
        stringBuilder.append("accessToken=" + this.getAccessToken() + "\n");
        stringBuilder.append("expiresIn=" + this.getExpiresIn() + "\n");
        stringBuilder.append("userName=" + this.getUserName() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
