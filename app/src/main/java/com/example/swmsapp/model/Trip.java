package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Trip {

    @SerializedName("id")
    private int trid;

    @SerializedName("startTime")

    private String startTime;

    @SerializedName("endTime")

    private String endTime;

    @SerializedName("distance")

    private double distance;

    @SerializedName("date")

    private String date;

    @SerializedName("truckid")

    private int tid;

    @SerializedName("tripqueryresult")

    private boolean tripqueryresult;

    public Trip(int trid, String startTime,String endTime, double distance, String date, int tid) {
        this.trid = trid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.date = date;
        this.tid = tid;
    }


    public int getTrid() {
        return trid;
    }

    public void setTrid(int trid) {
        this.trid = trid;
    }

    public String  getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public boolean isTripqueryresult() {
        return tripqueryresult;
    }
}
