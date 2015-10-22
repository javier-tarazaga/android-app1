
package com.tinygrip.android.data.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Preview Area Entity used in the data layer.
 */
public class PreviewAreaEntity {

    @Getter @Setter private String name;

    @Getter @Setter private float rating;

    @Getter @Setter private LocationEntity location;

    @Getter @Setter private LinkEntity self;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Preview Area Entity Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("rating=" + this.getRating() + "\n");
        stringBuilder.append("location=" + this.getLocation() + "\n");
        stringBuilder.append("self=" + this.getSelf() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
