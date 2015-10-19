package com.tinygrip.android.data.entity;

/**
 * Link Entity used in the data layer.
 */

public class LinkEntity {

    private String href;
    private boolean templated;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isTemplated() {
        return templated;
    }

    public void setTemplated(boolean templated) {
        this.templated = templated;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Link Entity Details *****\n");
        stringBuilder.append("href=" + this.getHref() + "\n");
        stringBuilder.append("templated=" + this.isTemplated() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
