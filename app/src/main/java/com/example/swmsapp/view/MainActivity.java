package com.example.swmsapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.swmsapp.R;
import com.example.swmsapp.controller.SWMSAppRetroift;
import com.example.swmsapp.controller.SharedPreferencesManger;
import com.example.swmsapp.model.Driver;
import com.example.swmsapp.model.Node;
import com.example.swmsapp.model.Truck;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    private static final int PERMISSIONS_REQUEST_LOCATION = 1;

    private LocationManager locationManager;
    private LocationListener locationListener;
    Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView swmsNavigation = findViewById(R.id.nav_view);
        View navigationHeader = swmsNavigation.getHeaderView(0);
        //show user data in the header of navigation drawer
        MaterialTextView showDriverId = navigationHeader.findViewById(R.id.showDriverId);
        MaterialTextView showDriverName = navigationHeader.findViewById(R.id.showDriverName);
        MaterialTextView showDriverPhone = navigationHeader.findViewById(R.id.showDriverPhone);
        MaterialTextView showDriverTruckId = navigationHeader.findViewById(R.id.showTruckId);
        showDriverId.setText(SharedPreferencesManger.getInstance(getApplicationContext()).getDrivers().getDid() + "");
        showDriverName.setText(SharedPreferencesManger.getInstance(getApplicationContext()).getDrivers().getDname());
        showDriverPhone.setText(SharedPreferencesManger.getInstance(getApplicationContext()).getDrivers().getPhone());
        showDriverTruckId.setText(SharedPreferencesManger.getInstance(getApplicationContext()).truckIdOfDriver() + "");
        //set toolbar and navigation drawer by code
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomnavigationView = findViewById(R.id.bottom_navigation);
        DrawerLayout drawerLayout = findViewById(R.id.navlayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();


        bottomnavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                if (item.getItemId() == R.id.country_map) {

                    //driver request the map so we need to get its location to root the driver to trip


                    fragment = new MapFragment();

                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);




                    // Check for location permission
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted, request it
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSIONS_REQUEST_LOCATION);
                    } else {
                        // Permission is granted, proceed to get location
                        startLocationUpdates();

                    }


                    if(bundle!=null){

                        fragment.setArguments(bundle);
                    }






                }else if(item.getItemId()==R.id.driver_trips){


                    fragment=new TripFragment();







                }

                else if(item.getItemId()==R.id.notification) {



                    fragment=new DriverTaskFragment();
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    // Check for location permission
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted, request it
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSIONS_REQUEST_LOCATION);
                    } else {
                        // Permission is granted, proceed to get location
                        startLocationUpdates();

                    }

                    if(bundle!=null){

                        fragment.setArguments(bundle);
                    }





                }




                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                //findViewById(R.id.bottom_navigation).setVisibility(View.GONE);//disappear bottom app bar

                return true;
            }
        });

        swmsNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_log_out) {

                    // log out
                    Call<List<Truck>> callUpdateTruckUsed = SWMSAppRetroift.getInstance("updateTruckUsedState").getSWMSApi().updateTruckUsed(SharedPreferencesManger.getInstance(getApplicationContext()).truckIdOfDriver(), 0);

                    callUpdateTruckUsed.enqueue(new Callback<List<Truck>>() {
                        @Override
                        public void onResponse(Call<List<Truck>> call, Response<List<Truck>> response) {

                            //update truck to be not used
                            List<Truck> truck_update = response.body();

                            //Toast.makeText(MainActivity.this, truck_update.get(0).getTid()+"", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<List<Truck>> call, Throwable t) {

                        }
                    });

                    SharedPreferencesManger.getInstance(getApplicationContext()).logOut();
                } else if (item.getItemId() == R.id.nav_home) {

                    // back to main activity

                    //startActivity(new Intent(MainActivity.this, MainActivity.class));



                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).addToBackStack(null).commit();
                    findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);//show bottom app bar
                } else if (item.getItemId() == R.id.nav_settings) {





                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new SettingsFragment()).addToBackStack(null).commit();
                    findViewById(R.id.bottom_navigation).setVisibility(View.GONE);//show bottom app bar
                }
                return true;
            }


        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted

                startLocationUpdates();

            } else {
                // Permission denied
                Toast.makeText(this, "لا يوجد سماحية لاستخدام الخريطة", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startLocationUpdates() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Location has changed

                //32.022291,35.840727

                //32.020255,35.838976

                //32.05209
                //35.874016
                double latitude =32.022291; //location.getLatitude();
                double longitude =35.840727;// location.getLongitude();
                int truckId=SharedPreferencesManger.getInstance(getApplicationContext()).truckIdOfDriver();
                // Do something with the location data
                //Toast.makeText(MainActivity.this, "Latitude: " + latitude + " Longitude: " + longitude+"did"+driverId+",truck id"+truckId, Toast.LENGTH_SHORT).show();

                //send latitude,longitude,truckid to map fragment to start the trip


                bundle.putDouble("lat",latitude);
                bundle.putDouble("long",longitude);
                bundle.putInt("tid",truckId);
                bundle.putInt("flag",0);//mean that driver want to start trip








            }


        };

        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop location updates to save battery
        locationManager.removeUpdates(locationListener);
    }





}