
package com.tinygrip.android.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Root Entity used in the data layer.
 */
public class RootEntity {

    private LinkEntity previewAreas;
    private LinkEntity areas;
    private RootAccountEntity account;

    public RootEntity() {
        //empty
    }

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

    public RootAccountEntity getAccount() {
        return account;
    }

    public void setAccount(RootAccountEntity account) {
        this.account = account;
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

        private LinkEntity authenticate;
        private LinkEntity register;
        private LinkEntity data;

        @JsonCreator
        public RootAccountEntity(@JsonProperty("Authenticate") LinkEntity authenticate,
                                 @JsonProperty("Register") LinkEntity register,
                                 @JsonProperty("Data") LinkEntity data) {
            this.authenticate = authenticate;
            this.register = register;
            this.data = data;
        }

        public LinkEntity getAuthenticate() {
            return authenticate;
        }

        public LinkEntity getRegister() {
            return register;
        }

        public LinkEntity getData() {
            return data;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("***** Root Account Entity Details *****\n");
            stringBuilder.append("authenticate=" + this.getAuthenticate() + "\n");
            stringBuilder.append("register=" + this.getRegister() + "\n");
            stringBuilder.append("data=" + this.getData() + "\n");
            stringBuilder.append("*******************************");

            return stringBuilder.toString();
        }
    }
}
