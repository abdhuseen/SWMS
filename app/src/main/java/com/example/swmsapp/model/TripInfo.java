package com.example.swmsapp.model;

public class TripInfo {

    private double distance;

    private double duration;

    private String mode;

    public TripInfo(double distance, double duration, String mode) {
        this.distance = distance;
        this.duration = duration;
        this.mode = mode;
    }

    public TripInfo() {

    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
