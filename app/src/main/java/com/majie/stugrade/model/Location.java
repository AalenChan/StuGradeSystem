package com.majie.stugrade.model;


public class Location {

    private int type;
    private long latitude;
    private long longitude;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
