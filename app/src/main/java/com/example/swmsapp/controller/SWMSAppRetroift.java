package com.example.swmsapp.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SWMSAppRetroift {

    private static SWMSAppRetroift instance=null;
    private final APISURL SWMSApi;

    private SWMSAppRetroift(String apiName){


        switch (apiName){

            case "updateTruckUsedState":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.updateTruckUsedURL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;

            case "availableTrucks":
            {
                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.availableTrucksURL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;



            case "rootingNodes":
            {
                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.getRootingNodesURL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;


            case "driverTrips":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.driverTripsURL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;

            case "tripNodes":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.TripNodesURL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;


            case "filledBins":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.FilledBinsAtNodeURL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;

            case "updateBinState":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.UPDATE_BIN_STATE_URL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;

            case "updateDriverData":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.UPDATE_DRIVER_DATA_URL).addConverterFactory(GsonConverterFactory.create())

                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;


            case "lastId":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.LAST_TRIP_ID_URL).addConverterFactory(GsonConverterFactory.create())

                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;

            case "addTrip":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.ADD_TRIP).addConverterFactory(GsonConverterFactory.create())

                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;

            case "addNodesToTrip":
            {

                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.ADD_NODES_TO_TRIP).addConverterFactory(GsonConverterFactory.create())

                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);

            }break;

            default: {
                Retrofit SWMSRetrofit = new Retrofit.Builder().baseUrl(APISURL.driverLoginURL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                SWMSApi = SWMSRetrofit.create(APISURL.class);
            }
        }

    }

    public static synchronized SWMSAppRetroift getInstance(String apiName){
        return instance=new SWMSAppRetroift(apiName);
    }
    public APISURL getSWMSApi(){
        return SWMSApi;
    }
}
