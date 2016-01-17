package com.tinygrip.android.presentation.model.area;

import com.tinygrip.android.domain.model.Location;
import com.tinygrip.android.domain.model.area.PreviewArea;
import java.io.Serializable;

/**
 * Class that represents an AreaPreview in the presentation layer.
 */
public class PreviewAreaModel implements Serializable {

    private final PreviewArea previewArea;
    private final String name;
    private final Location location;

    private float rating;

    public PreviewAreaModel(PreviewArea previewArea, String name, Location location) {
        this.previewArea = previewArea;
        this.name = name;
        this.location = location;
    }

    public PreviewArea getPreviewArea() {
        return previewArea;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Preview Area Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("rating=" + this.getRating() + "\n");
        stringBuilder.append("location=" + this.getLocation() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
