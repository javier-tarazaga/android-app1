
package com.tinygrip.android.data.entity.area;

import com.tinygrip.android.data.entity.LinkEntity;
import com.tinygrip.android.data.entity.area.route.PreviewRouteEntity;
import com.tinygrip.android.data.entity.area.wall.PreviewWallEntity;
import java.util.Collection;

/**
 * Area Entity used in the data layer.
 */
public class AreaEntity extends PreviewAreaEntity {

    private String description;
    private Collection<AreaImageEntity> images;
    private Collection<PreviewWallEntity> previewWalls;
    private Collection<PreviewRouteEntity> previewRoutes;
    private LinkEntity walls;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<AreaImageEntity> getImages() {
        return images;
    }

    public void setImages(Collection<AreaImageEntity> images) {
        this.images = images;
    }

    public Collection<PreviewWallEntity> getPreviewWalls() {
        return previewWalls;
    }

    public void setPreviewWalls(Collection<PreviewWallEntity> previewWalls) {
        this.previewWalls = previewWalls;
    }

    public Collection<PreviewRouteEntity> getPreviewRoutes() {
        return previewRoutes;
    }

    public void setPreviewRoutes(Collection<PreviewRouteEntity> previewRoutes) {
        this.previewRoutes = previewRoutes;
    }

    public LinkEntity getWalls() {
        return walls;
    }

    public void setWalls(LinkEntity walls) {
        this.walls = walls;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Area Entity Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("rating=" + this.getRating() + "\n");
        stringBuilder.append("ratingsCount=" + this.getRatingsCount() + "\n");
        stringBuilder.append("location=" + this.getLocation() + "\n");
        stringBuilder.append("images=" + this.getImages() + "\n");
        stringBuilder.append("previewWalls=" + this.getPreviewWalls() + "\n");
        stringBuilder.append("previewRoutes=" + this.getPreviewWalls() + "\n");
        stringBuilder.append("walls=" + this.getWalls() + "\n");
        stringBuilder.append("self=" + this.getSelf() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

    public static class AreaImageEntity {
        private String name;
        private LinkEntity link;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LinkEntity getLink() {
            return link;
        }

        public void setLink(LinkEntity link) {
            this.link = link;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("***** Area Image Entity Details *****\n");
            stringBuilder.append("name=" + this.getName() + "\n");
            stringBuilder.append("link=" + this.getLink() + "\n");
            stringBuilder.append("*******************************");

            return stringBuilder.toString();
        }
    }
}
