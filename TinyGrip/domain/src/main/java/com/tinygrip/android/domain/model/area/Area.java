
package com.tinygrip.android.domain.model.area;

import com.tinygrip.android.domain.model.Link;
import com.tinygrip.android.domain.model.Location;
import com.tinygrip.android.domain.model.area.route.PreviewRoute;
import com.tinygrip.android.domain.model.area.wall.PreviewWall;
import java.util.Collection;

/**
 * Class that represents an Area in the domain layer.
 */
public class Area extends PreviewArea {

    private String description;
    private Collection<AreaImage> images;
    private Collection<PreviewWall> previewWalls;
    private Collection<PreviewRoute> previewRoutes;
    private Link walls;

    public Area(String name, Location location, Link self) {
        super(name, location, self);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<AreaImage> getImages() {
        return images;
    }

    public void setImages(Collection<AreaImage> images) {
        this.images = images;
    }

    public Collection<PreviewWall> getPreviewWalls() {
        return previewWalls;
    }

    public void setPreviewWalls(Collection<PreviewWall> previewWalls) {
        this.previewWalls = previewWalls;
    }

    public Collection<PreviewRoute> getPreviewRoutes() {
        return previewRoutes;
    }

    public void setPreviewRoutes(Collection<PreviewRoute> previewRoutes) {
        this.previewRoutes = previewRoutes;
    }

    public Link getWalls() {
        return walls;
    }

    public void setWalls(Link walls) {
        this.walls = walls;
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
        stringBuilder.append("walls=" + this.getWalls() + "\n");
        stringBuilder.append("self=" + this.getSelf() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

    public static class AreaImage {
        private final String name;
        private final Link link;

        public AreaImage(String name, Link link) {
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

            stringBuilder.append("***** Area Image Details *****\n");
            stringBuilder.append("name=" + this.getName() + "\n");
            stringBuilder.append("link=" + this.getLink() + "\n");
            stringBuilder.append("*******************************");

            return stringBuilder.toString();
        }
    }
}
