package com.example.swmsapp.controller;

import com.example.swmsapp.model.DirectionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DirectionsService {

    @GET("directions/json")
    Call<DirectionResponse> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("waypoints") String waypoints,
            @Query("key") String apiKey
    );
}
