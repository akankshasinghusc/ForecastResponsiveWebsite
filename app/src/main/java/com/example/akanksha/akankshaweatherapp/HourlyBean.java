package com.example.akanksha.akankshaweatherapp;

/**
 * Created by AKANKSHA on 12/10/2015.
 */



public class HourlyBean {
    private String time;
    private String img;
    private String temp;

    public HourlyBean(String time, String img, String temp) {
        this.time = time;
        this.temp = temp;
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public String getImageId() {
        return img;
    }

    public String getTemp() {
        return temp;
    }
}

