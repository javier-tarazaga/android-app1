
package com.tinygrip.android.domain.model.area;

import com.tinygrip.android.domain.model.Link;
import com.tinygrip.android.domain.model.Location;
import java.io.Serializable;

/**
 * Class that represents a Preview Area in the domain layer.
 */
public class PreviewArea implements Serializable {

    private final String name;
    private final Location location;
    private final Link self;

    private float rating;

    public PreviewArea(String name, Location location, Link self) {
        this.name = name;
        this.location = location;
        this.self = self;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public Link getSelf() {
        return self;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Preview Area Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("rating=" + this.getRating() + "\n");
        stringBuilder.append("location=" + this.getLocation() + "\n");
        stringBuilder.append("self=" + this.getSelf() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
