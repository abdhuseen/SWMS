package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

public class Truck {

    @SerializedName("tid")
    private int tid;//truck id

    @SerializedName("tnumber")
    private int tnumber;//truck number

    @SerializedName("tplate")
    private String tplate; //truck plate

    @SerializedName("tcapacity")
    private double tcapacity;// truck capacity

    @SerializedName("tstate")
    private int  tstate; // truck state

    @SerializedName("tisused")
    private int  tisused;// truck isused

    @SerializedName("queryresult")
    private boolean queryResult;

    public Truck(int tid, int tnumber, String tplate, double tcapacity, int tstate, int tisused) {
        this.tid = tid;
        this.tnumber = tnumber;
        this.tplate = tplate;
        this.tcapacity = tcapacity;
        this.tstate = tstate;
        this.tisused = tisused;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getTnumber() {
        return tnumber;
    }

    public void setTnumber(int tnumber) {
        this.tnumber = tnumber;
    }

    public String getTplate() {
        return tplate;
    }

    public void setTplate(String tplate) {
        this.tplate = tplate;
    }

    public double getTcapacity() {
        return tcapacity;
    }

    public void setTcapacity(double tcapacity) {
        this.tcapacity = tcapacity;
    }

    public int getTstate() {
        return tstate;
    }

    public void setTstate(int tstate) {
        this.tstate = tstate;
    }

    public int getTisused() {
        return tisused;
    }

    public void setTisused(int tisused) {
        this.tisused = tisused;
    }

    public boolean isQueryResult() {
        return queryResult;
    }
}
