package com.example.swmsapp.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.swmsapp.R;
import com.example.swmsapp.model.Trip;
import com.example.swmsapp.view.MapFragment;
import com.example.swmsapp.view.TripFragment;

import java.util.List;

public class TripAdapter extends BaseAdapter {

    Context context;
    List<Trip> driverTrips;
    LayoutInflater inflater;
    public TripAdapter(Context context, List<Trip> driverTrips) {
        this.context = context;
        this.driverTrips = driverTrips;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return driverTrips.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.driver_trip_list,parent,false);
        TextView showTripID=convertView.findViewById(R.id.showTripId);

        TextView showTripSTime=convertView.findViewById(R.id.showTripStartTime);

        TextView showTripETime=convertView.findViewById(R.id.showTripEndTime);

        TextView showTripDistance=convertView.findViewById(R.id.showTripDistance);

        TextView showTripDate=convertView.findViewById(R.id.showTripDate);

        TextView showTripTruckId=convertView.findViewById(R.id.showTripTruck);

        Button showTripOnMap=convertView.findViewById(R.id.btn_show_on_map);

        showTripID.setText(driverTrips.get(position).getTrid()+"");

        showTripSTime.setText(driverTrips.get(position).getStartTime()+"");

        showTripETime.setText(driverTrips.get(position).getEndTime()+"");

        showTripDistance.setText(driverTrips.get(position).getDistance()+"");

        showTripDate.setText(driverTrips.get(position).getDate()+"");

        showTripTruckId.setText(driverTrips.get(position).getTid()+"");


        showTripOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int tripId=Integer.parseInt(showTripID.getText()+"");


                Bundle bundle=new Bundle();
                bundle.putInt("tripId",tripId);
                bundle.putInt("flag",1);


                MapFragment mapFragment=new MapFragment();

                mapFragment.setArguments(bundle);


                AppCompatActivity activity = (AppCompatActivity)v.getContext();


                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,mapFragment).addToBackStack(null).commit();




            }
        });



        return convertView;
    }
}
