
package com.tinygrip.android.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents a Link in the domain layer.
 */
public class Link {

    @Getter private final String href;
    @Getter private final boolean templated;

    public Link(String href, boolean templated) {
        this.href = href;
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