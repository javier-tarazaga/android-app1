package com.tinygrip.android.presentation.model.area.wall;

import com.tinygrip.android.domain.model.Link;
import com.tinygrip.android.domain.model.Location;
import com.tinygrip.android.domain.model.area.wall.PreviewWall;

/**
 * Preview Wall used in the presentation layer.
 */
public class PreviewWallModel {

    private final PreviewWall previewWall;
    private final String name;
    private final Location location;
    
    private Link image;

    public PreviewWallModel(PreviewWall previewWall, String name, Location location) {
        this.previewWall = previewWall;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Preview Wall Presentation Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("location=" + this.getLocation() + "\n");
        stringBuilder.append("image=" + this.getImage() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
