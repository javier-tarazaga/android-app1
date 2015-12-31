
package com.tinygrip.android.domain.model;

/**
 * Class that represents a User in the domain layer.
 */
public class PreviewArea {

    private final String name;
    private float rating;
    private Location location;
    private Link self;

    public PreviewArea(String name) {
        this.name = name;
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

    public void setLocation(Location location) {
        this.location = location;
    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

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
