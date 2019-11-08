package com.tgy.nearbyredis.utils;

/**
 * @author DragonSwimDiving
 * @program nearbyredis
 * @Date 2019-11-07 16:11
 **/

public class Coordinate {
    private Double latitude;
    private Double longitude;
    private String key;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
