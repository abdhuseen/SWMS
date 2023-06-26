package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

public class Node {

    @SerializedName("id")
    private int nid;

    @SerializedName("latitude")
    private double nlatitude;

    @SerializedName("longitude")
    private double nlongitude;

    @SerializedName("street")
    private String nstreet;



    public Node(int nid, double nlatitude, double nlongitude, String nstreet) {
        this.nid = nid;
        this.nlatitude = nlatitude;
        this.nlongitude = nlongitude;
        this.nstreet = nstreet;
    }



    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public double getNlatitude() {
        return nlatitude;
    }

    public void setNlatitude(double nlatitude) {
        this.nlatitude = nlatitude;
    }

    public double getNlongitude() {
        return nlongitude;
    }

    public void setNlongitude(double nlongitude) {
        this.nlongitude = nlongitude;
    }

    public String getNstreet() {
        return nstreet;
    }

    public void setNstreet(String nstreet) {
        this.nstreet = nstreet;
    }


}
