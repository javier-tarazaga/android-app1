package com.tinygrip.android.data.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * User Auth Entity used in the data layer. This will be the object returned by the backend when performing an
 * authentication
 * against it in order to generate a new user Token to be used throughout the application.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OAuthEntity {

    @JsonProperty("access_token") @Getter @Setter private String accessToken;

    @JsonProperty("expires_in") @Getter @Setter private int expiresIn;

    @JsonProperty("userName") @Getter @Setter private String userName;

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
