package com.tinygrip.android.data.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Link Entity used in the data layer.
 */

public class LinkEntity {

    @Getter @Setter private String href;

    @Getter @Setter private boolean templated;

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
