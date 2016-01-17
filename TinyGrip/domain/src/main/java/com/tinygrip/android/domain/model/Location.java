package com.tinygrip.android.domain.model;

import java.io.Serializable;

/**
 * Location Entity used in the domain layer.
 */
public class Location implements Serializable {

    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Location Details *****\n");
        stringBuilder.append("latitude=" + this.getLatitude() + "\n");
        stringBuilder.append("longitude=" + this.getLongitude() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

}
