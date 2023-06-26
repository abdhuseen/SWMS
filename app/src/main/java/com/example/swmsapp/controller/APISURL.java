package com.example.swmsapp.controller;

import com.example.swmsapp.model.Bin;
import com.example.swmsapp.model.Driver;
import com.example.swmsapp.model.LastTripId;
import com.example.swmsapp.model.Node;
import com.example.swmsapp.model.Trip;
import com.example.swmsapp.model.TripPath;
import com.example.swmsapp.model.Truck;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APISURL {

    String serverIp="iotswms2.000webhostapp.com";
///
   String driverLoginURL="http://"+serverIp+"/smartWasteServer/driverLogin.php/";

   String availableTrucksURL="http://"+serverIp+"/smartWasteServer/readAllAvailableTrucks.php/";

   String updateTruckUsedURL="http://"+serverIp+"/smartWasteServer/updateTruckUsedState.php/";


    String getRootingNodesURL="http://"+serverIp+"/smartWasteServer/getRootingNodes.php/";

    String driverTripsURL="http://"+serverIp+"/smartWasteServer/driverTrips.php/";

    String TripNodesURL="http://"+serverIp+"/smartWasteServer/getTripNodes.php/";


 String FilledBinsAtNodeURL="http://"+serverIp+"/smartWasteServer/getFilledBinsAtNode.php/";


    String UPDATE_BIN_STATE_URL="http://"+serverIp+"/smartWasteServer/updateBinState.php/";

    String UPDATE_DRIVER_DATA_URL="http://"+serverIp+"/smartWasteServer/updateDriverData.php/";

    String LAST_TRIP_ID_URL="http://"+serverIp+"/smartWasteServer/getLastTripId.php/";

    String ADD_TRIP="http://"+serverIp+"/smartWasteServer/addTrip.php/";


    String ADD_NODES_TO_TRIP="http://"+serverIp+"/smartWasteServer/addNodesToTrip.php/";


    @FormUrlEncoded
    @POST("driverLogin.php")
    Call<List<Driver>> driverLogin(@Field("DId")int id, @Field("Dpass") String password);

    @GET("readAllAvailableTrucks.php")
    Call<List<Truck>> availableTrucks();

    @FormUrlEncoded
    @POST("updateTruckUsedState.php")
    Call<List<Truck>>updateTruckUsed(@Field("tId") int tid,@Field("tisused") int usedState );


    @FormUrlEncoded
    @POST("getRootingNodes.php")
    Call<List<Node>>getRootingNodes(@Field("TId") int tid, @Field("latitude") double dLatitude,
                                    @Field("longitude") double dLongitude);


    @FormUrlEncoded

    @POST("driverTrips.php")

    Call<List<Trip>>getDriverTrips(@Field("DId") int driverId);


    @FormUrlEncoded

    @POST("getTripNodes.php")

    Call<List<Node>>getNodesOfTrip(@Field("TrId") int tripId);

 @FormUrlEncoded

 @POST("getFilledBinsAtNode.php")

 Call<List<Bin>>getFilledBinsAtNode(@Field("lat") double latitude,@Field("long") double longitude);


    @FormUrlEncoded

    @POST("updateBinState.php")

    Call<List<Bin>>updateBinState(@Field("bId") int binid);

    @FormUrlEncoded

    @POST("updateDriverData.php")

    Call<List<Driver>>updateDriverData(@Field("dId") int did,
                                      @Field("dname") String dname,
                                      @Field("dssn") String dssn,
                                      @Field("dBdate") String dBdate,
                                      @Field("dSalary") double dsalary,
                                      @Field("dCity") String dcity,
                                      @Field("dPhone") String dphone,
                                      @Field("dPassword") String dpassword

    );

    @GET("getLastTripId.php")

    Call<List<LastTripId>>getLastId();


    @FormUrlEncoded

    @POST("addTrip.php")

    Call<List<Trip>>addTrip(@Field("lastId") int lastId,
                            @Field("startTime") String startTime,
                            @Field("endTime") String endTime,
                            @Field("distance") double distance,
                            @Field("date") String date,
                            @Field("truckId") int truckId,
                            @Field("driverId") int driverId);

    @FormUrlEncoded

   @POST("addNodesToTrip.php")

   Call<List<TripPath>>addNodesToTrip(@Field("trId") int tripId,@Field("nid") int nodeId);

}
