package com.example.location;

public class MySilencers {
    String silencerTitle,key,location1,latitude,longitude;

    public MySilencers() {
    }

    public MySilencers(String silencerTitle,  String key, String location, String latitude, String longitude) {
        this.silencerTitle = silencerTitle;
        this.key = key;
        this.location1 = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getSilencerTitle() {
        return silencerTitle;
    }

    public void setSilencerTitle(String silencerTitle) {
        this.silencerTitle = silencerTitle;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location1;
    }

    public void setLocation(String location) {
        this.location1 = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
