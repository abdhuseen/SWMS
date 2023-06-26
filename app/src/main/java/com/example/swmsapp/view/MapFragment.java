package com.example.swmsapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.swmsapp.controller.DirectionsService;
import com.example.swmsapp.model.DirectionResponse;
import com.example.swmsapp.model.Leg;
import com.example.swmsapp.model.Polyline;
import com.example.swmsapp.model.Route;
import com.example.swmsapp.model.TripInfo;
import com.google.gson.Gson;
import  com.google.maps.android.PolyUtil;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import com.example.swmsapp.R;
import com.example.swmsapp.controller.SWMSAppRetroift;
import com.example.swmsapp.controller.SharedPreferencesManger;
import com.example.swmsapp.model.LastTripId;
import com.example.swmsapp.model.Node;
import com.example.swmsapp.model.Trip;
import com.example.swmsapp.model.TripPath;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import kotlin.jvm.Synchronized;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapFragment extends Fragment implements GoogleMap.OnMarkerClickListener {



    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");



    private static final String API_KEY = "AIzaSyCl5oMQ1dPd_fHJ3xOhRzeHJsMAHrIz4vU";


    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/directions/json?";





    private OnMapReadyCallback callback = new OnMapReadyCallback() {



        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         *
         */


        @Override
        public void onMapReady(GoogleMap googleMap) {

            int truckId;
            double latitude, longitude;
            Bundle args=getArguments();
            LatLng area_in_amman = new LatLng(32.020255,
            35.838976);
            googleMap.addMarker(new MarkerOptions().position(area_in_amman).title("موقعك الحالي"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(area_in_amman));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(area_in_amman).zoom(15).tilt(0).bearing(30).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);





            googleMap.setOnMarkerClickListener(MapFragment.this);//set the method on marker clicked

            LatLng start = new LatLng(32.020255, 35.838976); // replace with the actual coordinates of the first marker
            LatLng end = new LatLng(32.050184
                    , 35.873317); // replace with the actual coordinates of the second marker





           if (args != null&&args.getInt("flag")==0) {

               //draw the route to generate trip for driver

                truckId =args.getInt("tid");

                latitude = args.getDouble("lat");

                longitude =args.getDouble("long");


                Call<List<Node>> call = SWMSAppRetroift.getInstance("rootingNodes").getSWMSApi().getRootingNodes(
                        truckId, latitude, longitude
                );

                call.enqueue(new Callback<List<Node>>() {
                    @Override
                    public void onResponse(Call<List<Node>> call, Response<List<Node>> response) {
                        List<Node> rootingNodes = response.body();

                        if(rootingNodes!=null){


                            for(Node n:rootingNodes){

                                LatLng marker = new LatLng(n.getNlatitude(), n.getNlongitude());
                                googleMap.addMarker(new MarkerOptions().position(marker).title(n.getNstreet()));




                            }

                           if(rootingNodes.size()==0){
                               //trip end
                               enableTrip();
                               SharedPreferencesManger.getInstance(getActivity()).setEnabled(0);
                           }


                          if(SharedPreferencesManger.getInstance(getActivity()).getEnable()==0){


                              Toast.makeText(getActivity(), "انتا غير متاح حاليا للقيام برحلات", Toast.LENGTH_SHORT).show();

                              AppCompatActivity activity = (AppCompatActivity)getActivity();


                              activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).addToBackStack(null).commit();
                              return;
                          }




                          //flag is zero so it request a trip
                           drawRoute(googleMap,rootingNodes,Color.BLUE,10,0);


                        }




                    }

                    @Override
                    public void onFailure(Call<List<Node>> call, Throwable t) {

                    }
                });


            }else if(args!=null&&args.getInt("flag")==1){

               //draw trip path


               int tripId=args.getInt("tripId");

               Call<List<Node>> call = SWMSAppRetroift.getInstance("tripNodes").getSWMSApi().getNodesOfTrip(tripId);
               call.enqueue(new Callback<List<Node>>() {
                   @Override
                   public void onResponse(Call<List<Node>> call, Response<List<Node>> response) {
                       List<Node> NodesOfTrip = response.body();

                       if(NodesOfTrip.get(0).getNid()>0){

                           for(Node n:NodesOfTrip){

                               LatLng marker = new LatLng(n.getNlatitude(), n.getNlongitude());
                               googleMap.addMarker(new MarkerOptions().position(marker).title(n.getNstreet()));



                           }
                           //flag =1 its show trip
                           drawRoute(googleMap,NodesOfTrip,Color.RED,10,1);
                       }

                   }

                   @Override
                   public void onFailure(Call<List<Node>> call, Throwable t) {

                   }
               });


           }





        }
    };







    //*******************




    private  TripInfo drawRoute(GoogleMap googleMap, List<Node> rootingNodes, int color, float width,int flag) {

        TripInfo tripInfo=new TripInfo();

        if (rootingNodes != null) {
            // Create a list of LatLng for the waypoints
            List<LatLng> waypoints = new ArrayList<>();
            for (Node node : rootingNodes) {
                LatLng marker = new LatLng(node.getNlatitude(), node.getNlongitude());
                waypoints.add(marker);
            }

            // Prepare the request URL for the Directions API
            StringBuilder waypointsBuilder = new StringBuilder();
            for (int i = 1; i < waypoints.size() - 1; i++) {
                waypointsBuilder.append("via:").append(waypoints.get(i).latitude).append(",").append(waypoints.get(i).longitude);
                if (i < waypoints.size() - 2) {
                    waypointsBuilder.append("|");
                }
            }

            // Create Retrofit instance


             Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Create the service
            DirectionsService service = retrofit.create(DirectionsService.class);

            // Make the API call
            Call<DirectionResponse> call = service.getDirections(
                    waypoints.get(0).latitude + "," + waypoints.get(0).longitude,
                    waypoints.get(waypoints.size() - 1).latitude + "," + waypoints.get(waypoints.size() - 1).longitude,
                    waypointsBuilder.toString(),
                    API_KEY
            );


            // Asynchronously handle the response
            call.enqueue(new Callback<DirectionResponse>() {
                @Override
                public void onResponse(Call<DirectionResponse> call, Response<DirectionResponse> response) {
                    if (response.isSuccessful()) {
                        DirectionResponse directionResponse = response.body();
                        if (directionResponse != null) {
                            List<Route> routes = directionResponse.getRoutes();
                            if (!routes.isEmpty()) {
                                Route route = routes.get(0);
                                Polyline polyline = route.getOverviewPolyline();
                                String points = polyline.getPoints();

                                // Decode the polyline points
                                List<LatLng> decodedPoints = PolyUtil.decode(points);

                                // Draw the route on the map
                                PolylineOptions polylineOptions = new PolylineOptions()
                                        .color(color)
                                        .width(width)
                                        .addAll(decodedPoints);
                                googleMap.addPolyline(polylineOptions);


                                // Get total distance and duration for the route
                                int totalDistance = 0;
                                int totalDuration = 0;
                                for (Leg leg : route.getLeg()) {
                                    totalDistance += leg.getDistance().getValue();
                                    totalDuration += leg.getDuration().getValue();
                                }


                                tripInfo.setDuration(totalDuration);

                                tripInfo.setDistance(totalDistance);

                                tripInfo.setMode("D");

                                //
                                //
                                if(flag==0 &&SharedPreferencesManger.getInstance(getActivity()).getEnable()==1
                                &&SharedPreferencesManger.getInstance(getActivity()).getEndTrip()==true){
                                    askForTrip(tripInfo,rootingNodes);





                                }



                                Log.d("Route Info", "Total Distance: " + totalDistance + " meters, Total Duration: " + totalDuration + " seconds");

                                //Toast.makeText(getActivity(), totalDistance+"m"+","+totalDuration+"sec", Toast.LENGTH_SHORT).show();

                            }





                        }
                    } else {
                        // Handle API error
                        Log.e("API Error", "Response code: " + response.code());

                    }


                }

                @Override
                public void onFailure(Call<DirectionResponse> call, Throwable t) {
                    // Handle network failure
                    t.printStackTrace();
                }
            });




        }

        return tripInfo;
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);

        }





    }


    private void askForTrip(TripInfo tripInfo,List<Node>rootingNodes){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("المسافة:"+Math.round(tripInfo.getDistance()/1000)+"كم\n"+"الوقت:"+Math.round(tripInfo.getDuration()/60)+"دقيقة\n"+"الطريقة:"+"قيادة");
        builder.setMessage("هل تريد بدء الرحلة");

        //insert trip to db
        builder.setPositiveButton("بدء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle positive button click
                disableTrip();
                Call<List<LastTripId>>call2=SWMSAppRetroift.getInstance("lastId").getSWMSApi().getLastId();

                call2.enqueue(new Callback<List<LastTripId>>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(Call<List<LastTripId>> call, Response<List<LastTripId>> response) {
                        List<LastTripId>result=response.body();

                        if(result.get(0).isQueryResult()){

                            int  lastid=result.get(0).getLastTripId();

                            //get start Time
                            LocalTime currentTime = LocalTime.now();

                            String startTime=currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond();


                            //get end time
                            LocalTime timeOfTripEnd=currentTime.plusMinutes((long) (tripInfo.getDuration())/60);

                            String endTime=timeOfTripEnd.getHour()+":"+timeOfTripEnd.getMinute()+":"+timeOfTripEnd.getSecond();

                            double distance=(tripInfo.getDistance())/1000;

                            int truckId= SharedPreferencesManger.getInstance(getActivity()).truckIdOfDriver();

                            int driverId=SharedPreferencesManger.getInstance(getActivity()).getDrivers().getDid();

                            LocalDate currentDate = LocalDate.now();

                            String date=currentDate.getYear()+"-"+currentDate.getMonthValue()+"-"+currentDate.getDayOfMonth();
                            createTrip(startTime,endTime,distance,date,truckId,driverId,lastid,rootingNodes);



                        }
                    }

                    @Override
                    public void onFailure(Call<List<LastTripId>> call, Throwable t) {

                    }
                });

            }
        });

        builder.setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle negative button click
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {

        double latitude=marker.getPosition().latitude;

        double longitude=marker.getPosition().longitude;


        Bundle bundle=new Bundle();

        bundle.putDouble("lat",latitude);

        bundle.putDouble("long",longitude);

        BinFragment binFragment=new BinFragment();

        binFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,binFragment).addToBackStack(null).commit();



        return true;
    }


    private void createTrip(String startTime,String endTime,double distance,String date,int truckId,int driverId,int LastTripId
            ,List<Node> tripRootingNodes){

      //add trip
        Call<List<Trip>>call=SWMSAppRetroift.getInstance("addTrip").getSWMSApi().addTrip(
                LastTripId+1,
                startTime,
                endTime,
                distance,
                date,
                truckId,
                driverId
        );

        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {

                if(response.body().get(0).isTripqueryresult()){



                    addNodesToTrip(LastTripId+1,tripRootingNodes);





                }

            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

            }
        });





    }

    private void addNodesToTrip(int tripId, List<Node> nodes) {


        for(Node node:nodes){


            Call<List<TripPath>>call=SWMSAppRetroift.getInstance("addNodesToTrip").getSWMSApi().addNodesToTrip(tripId,node.getNid());

            call.enqueue(new Callback<List<TripPath>>() {
                @Override
                public void onResponse(Call<List<TripPath>> call, Response<List<TripPath>> response) {

                    List<TripPath>list=response.body();

                    if(!list.get(0).isQueryResult()){

                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<TripPath>> call, Throwable t) {

                }
            });


        }


    }

    private void enableTrip(){

        SharedPreferencesManger.getInstance(getActivity()).setEndTrip(true);

    }

    private void disableTrip(){

        SharedPreferencesManger.getInstance(getActivity()).setEndTrip(false);
    }

    }
































