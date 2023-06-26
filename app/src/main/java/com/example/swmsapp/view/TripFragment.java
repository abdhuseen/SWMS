package com.example.swmsapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ListView;
import android.widget.Toast;

import com.example.swmsapp.R;
import com.example.swmsapp.controller.SWMSAppRetroift;
import com.example.swmsapp.controller.SharedPreferencesManger;
import com.example.swmsapp.controller.TripAdapter;
import com.example.swmsapp.model.Trip;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TripFragment extends Fragment {


    public TripFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView driverTripsList=view.findViewById(R.id.TripListView);

        int driverId= SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getDid();

        Call<List<Trip>> call= SWMSAppRetroift.getInstance("driverTrips").getSWMSApi().getDriverTrips(driverId);

        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {


                if(response.body().size()>0){

                    driverTripsList.setAdapter(new TripAdapter(view.getContext(),response.body()));
                }else {

                    List<Trip>emptyTrips=new ArrayList<>();

                    emptyTrips.add(new Trip(0,"0:0:0","0:0:0",0,"00-00-00",0));

                    driverTripsList.setAdapter(new TripAdapter(view.getContext(),emptyTrips));

                    Toast.makeText(view.getContext(), "لا يوجد رحلات خاصة بك", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });







    }
}