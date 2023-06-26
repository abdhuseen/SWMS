package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

public class Bin {

    @SerializedName("id")
    private int binid;

    @SerializedName("state")
    private int state;

    public Bin(int binid, int state) {
        this.binid = binid;
        this.state = state;
    }

    public int getBinid() {
        return binid;
    }

    public void setBinid(int binid) {
        this.binid = binid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
