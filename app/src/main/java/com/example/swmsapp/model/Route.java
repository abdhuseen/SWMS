package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Route {

    @SerializedName("overview_polyline")
    private Polyline overviewPolyline;

    @SerializedName("legs")
    private List <Leg> leg;

    public List <Leg> getLeg() {
        return leg;
    }

    public Polyline getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(Polyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }
}
