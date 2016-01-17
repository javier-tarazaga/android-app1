
package com.tinygrip.android.data.entity.area;

import com.tinygrip.android.data.entity.LinkEntity;
import com.tinygrip.android.data.entity.LocationEntity;

/**
 * Preview Area Entity used in the data layer.
 */
public class PreviewAreaEntity {

    private String name;
    private float rating;
    private float ratingsCount;
    private LocationEntity location;
    private LinkEntity self;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(float ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
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

        stringBuilder.append("***** Preview Area Entity Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("rating=" + this.getRating() + "\n");
        stringBuilder.append("ratingsCount=" + this.getRatingsCount() + "\n");
        stringBuilder.append("location=" + this.getLocation() + "\n");
        stringBuilder.append("self=" + this.getSelf() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }


}
