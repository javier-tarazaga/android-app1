package com.tinygrip.android.data.entity.area.wall;

import com.tinygrip.android.data.entity.LinkEntity;
import com.tinygrip.android.data.entity.LocationEntity;

/**
 * Preview Wall Entity used in the data layer.
 */
public class PreviewWallEntity {
    private String name;
    private LocationEntity location;
    private LinkEntity image;
    private LinkEntity area;
    private LinkEntity routes;
    private LinkEntity self;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public LinkEntity getImage() {
        return image;
    }

    public void setImage(LinkEntity image) {
        this.image = image;
    }

    public LinkEntity getArea() {
        return area;
    }

    public void setArea(LinkEntity area) {
        this.area = area;
    }

    public LinkEntity getRoutes() {
        return routes;
    }

    public void setRoutes(LinkEntity routes) {
        this.routes = routes;
    }

    public LinkEntity getSelf() {
        return self;
    }

    public void setSelf(LinkEntity self) {
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
