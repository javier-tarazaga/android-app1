
package com.tinygrip.android.presentation.model.area;

import com.tinygrip.android.domain.model.Link;
import com.tinygrip.android.domain.model.Location;
import com.tinygrip.android.domain.model.area.Area;
import com.tinygrip.android.presentation.model.area.route.PreviewRouteModel;
import com.tinygrip.android.presentation.model.area.wall.PreviewWallModel;
import java.util.Collection;

/**
 * Class that represents an Area in the presentation layer.
 */
public class AreaModel extends PreviewAreaModel {

    private String description;
    private Collection<AreaImageModel> images;
    private Collection<PreviewWallModel> previewWalls;
    private Collection<PreviewRouteModel> previewRoutes;


    public AreaModel(Area area, String name, Location location) {
        super(area, name, location);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<AreaImageModel> getImages() {
        return images;
    }

    public void setImages(Collection<AreaImageModel> images) {
        this.images = images;
    }

    public Collection<PreviewWallModel> getPreviewWalls() {
        return previewWalls;
    }

    public void setPreviewWalls(Collection<PreviewWallModel> previewWalls) {
        this.previewWalls = previewWalls;
    }

    public Collection<PreviewRouteModel> getPreviewRoutes() {
        return previewRoutes;
    }

    public void setPreviewRoutes(Collection<PreviewRouteModel> previewRoutes) {
        this.previewRoutes = previewRoutes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Area Entity Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("rating=" + this.getRating() + "\n");
        stringBuilder.append("location=" + this.getLocation() + "\n");
        stringBuilder.append("images=" + this.getImages() + "\n");
        stringBuilder.append("previewWalls=" + this.getPreviewWalls() + "\n");
        stringBuilder.append("previewRoutes=" + this.getPreviewWalls() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

    public static class AreaImageModel {
        private final String name;
        private final Link link;

        public AreaImageModel(String name, Link link) {
            this.name = name;
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public Link getLink() {
            return link;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("***** Area Image Presentation Details *****\n");
            stringBuilder.append("name=" + this.getName() + "\n");
            stringBuilder.append("link=" + this.getLink() + "\n");
            stringBuilder.append("*******************************");

            return stringBuilder.toString();
        }
    }
}
