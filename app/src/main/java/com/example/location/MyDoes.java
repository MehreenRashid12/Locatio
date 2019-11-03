package com.example.location;

public class MyDoes {
    String titleDoes,dateDoes,keyDoes,locationDoes,latitudeDoes,longitudeDoes;

    public MyDoes() {
    }

    public MyDoes(String titleDoes, String dateDoes, String keyDoes, String locationDoes, String latitudeDoes, String longitudeDoes) {
        this.titleDoes = titleDoes;
        this.dateDoes = dateDoes;
        this.keyDoes = keyDoes;
        this.locationDoes = locationDoes;
        this.latitudeDoes = latitudeDoes;
        this.longitudeDoes = longitudeDoes;
    }

    public String getKeyDoes() {
        return keyDoes;
    }

    public void setKeyDoes(String keyDoes) {
        this.keyDoes = keyDoes;
    }

    public String getTitleDoes() {
        return titleDoes;
    }

    public void setTitleDoes(String titleDoes) {
        this.titleDoes = titleDoes;
    }

    public String getDateDoes() {
        return dateDoes;
    }


    public void setDateDoes(String dateDoes) {
        this.dateDoes = dateDoes;
    }

    public String getLocationDoes() {
        return locationDoes;
    }

    public void setLocationDoes(String locationDoes) {
        this.locationDoes = locationDoes;
    }

    public String getLatitudeDoes() {
        return latitudeDoes;
    }

    public void setLatitudeDoes(String latitudeDoes) {
        this.latitudeDoes = latitudeDoes;
    }


    public String getLongitudeDoes() {
        return longitudeDoes;
    }

    public void setLongitudeDoes(String longitudeDoes) {
        this.longitudeDoes = longitudeDoes;
    }
}
