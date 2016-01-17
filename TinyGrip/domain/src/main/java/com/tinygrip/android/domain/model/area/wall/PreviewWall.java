package com.tinygrip.android.domain.model.area.wall;

import com.tinygrip.android.domain.model.Link;
import com.tinygrip.android.domain.model.Location;

/**
 * Preview Wall used in the domain layer.
 */
public class PreviewWall {
    
    private final String name;
    private final Location location;
    
    private Link image;
    private Link area;
    private Link routes;
    private Link self;

    public PreviewWall(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Link getImage() {
        return image;
    }

    public void setImage(Link image) {
        this.image = image;
    }

    public Link getArea() {
        return area;
    }

    public void setArea(Link area) {
        this.area = area;
    }

    public Link getRoutes() {
        return routes;
    }

    public void setRoutes(Link routes) {
        this.routes = routes;
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

        stringBuilder.append("***** Preview Wall Entity Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("location=" + this.getLocation() + "\n");
        stringBuilder.append("image=" + this.getImage() + "\n");
        stringBuilder.append("area=" + this.getArea() + "\n");
        stringBuilder.append("routes=" + this.getRoutes() + "\n");
        stringBuilder.append("self=" + this.getSelf() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
