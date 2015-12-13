
package com.tinygrip.android.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Root Entity used in the data layer.
 */
public class RootEntity {

    @Getter @Setter private LinkEntity previewAreas;

    @Getter @Setter private LinkEntity areas;

    @Getter @Setter private RootAccountEntity account;

    public RootEntity() {
        //empty
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Root Entity Details *****\n");
        stringBuilder.append("previewAreas=" + this.getPreviewAreas() + "\n");
        stringBuilder.append("areas=" + this.getAreas() + "\n");
        stringBuilder.append("account=" + this.getAccount() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

    public static class RootAccountEntity {

        @Getter private final LinkEntity login;

        @Getter private final LinkEntity register;

        @Getter private final LinkEntity data;

        @JsonCreator
        public RootAccountEntity(@JsonProperty("Login") LinkEntity login,
                                 @JsonProperty("Register") LinkEntity register,
                                 @JsonProperty("Data") LinkEntity data) {
            this.login = login;
            this.register = register;
            this.data = data;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("***** Root Account Entity Details *****\n");
            stringBuilder.append("login=" + this.getLogin() + "\n");
            stringBuilder.append("register=" + this.getRegister() + "\n");
            stringBuilder.append("data=" + this.getData() + "\n");
            stringBuilder.append("*******************************");

            return stringBuilder.toString();
        }
    }
}
