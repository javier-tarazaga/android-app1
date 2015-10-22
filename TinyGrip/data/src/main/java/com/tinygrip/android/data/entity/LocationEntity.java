package com.tinygrip.android.data.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Location Entity used in the data layer.
 */
public class LocationEntity {

    @Getter @Setter private double latitude;

    @Getter @Setter private double longitude;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Location Entity Details *****\n");
        stringBuilder.append("latitude=" + this.getLatitude() + "\n");
        stringBuilder.append("longitude=" + this.getLongitude() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
