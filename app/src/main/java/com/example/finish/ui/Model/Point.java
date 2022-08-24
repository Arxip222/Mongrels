package com.example.finish.ui.Model;
public class Point {
    public static double latitude;
    public static double longitude;
    public static String id;

    public Point() {
    }

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

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
}