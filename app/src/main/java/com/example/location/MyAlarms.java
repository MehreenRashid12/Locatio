package com.example.location;

public class MyAlarms {
    String alarmTitle,key,location,latitude,longitude;

    public MyAlarms() {
    }

    public MyAlarms(String alarmTitle,  String key, String location, String latitude, String longitude) {
        this.alarmTitle = alarmTitle;
        this.key = key;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public void setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

