package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

public class TripPath {

    @SerializedName("trid")
    private int trid;

    @SerializedName("nid")
    private int nid;

    @SerializedName("queryresult")
    private boolean queryResult;

    public int getTrid() {
        return trid;
    }

    public void setTrid(int trid) {
        this.trid = trid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public boolean isQueryResult() {
        return queryResult;
    }

    public void setQueryResult(boolean queryResult) {
        this.queryResult = queryResult;
    }
}
