package com.tinygrip.android.data.entity;

/**
 * Location Entity used in the data layer.
 */
public class LocationEntity {

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

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
