package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

public class LastTripId {

    @SerializedName("id")
    private int lastTripId;

    @SerializedName("queryresult")
    private boolean queryResult;

    public int getLastTripId() {
        return lastTripId;
    }

    public void setLastTripId(int lastTripId) {
        this.lastTripId = lastTripId;
    }

    public boolean isQueryResult() {
        return queryResult;
    }

    public void setQueryResult(boolean queryResult) {
        this.queryResult = queryResult;
    }
}
