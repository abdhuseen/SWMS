package com.example.swmsapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.swmsapp.R;
import com.example.swmsapp.controller.BinAdapter;
import com.example.swmsapp.controller.SWMSAppRetroift;
import com.example.swmsapp.controller.SharedPreferencesManger;
import com.example.swmsapp.model.Bin;
import com.example.swmsapp.model.Trip;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BinFragment extends Fragment {

public static int counter=0;

    public BinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView filledBinsList=view.findViewById(R.id.FilledBinsListView);

        Button clearNode=view.findViewById(R.id.button_setNodeEmpty);

        Bundle bundle=getArguments();

        if(bundle!=null){




            double lat=bundle.getDouble("lat");

            double longitude=bundle.getDouble("long");

            retrofit2.Call<List<Bin>> call= SWMSAppRetroift.getInstance("filledBins").getSWMSApi().getFilledBinsAtNode(lat,longitude);

            call.enqueue(new Callback<List<Bin>>() {
                @Override
                public void onResponse(Call<List<Bin>> call, Response<List<Bin>> response) {
                    List<Bin>filledBins=response.body();


                    if(filledBins.get(0).getBinid()>0){

                        filledBinsList.setAdapter(new BinAdapter(view.getContext(),filledBins));

                        clearNode.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                clearNode(filledBins);
                                Toast.makeText(v.getContext(),"تم تفريغ الحاويات",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<List<Bin>> call, Throwable t) {

                }
            });



        }


    }


    private void clearNode(List<Bin>binsAtNode){

        for(Bin bin :binsAtNode){


            Call<List<Bin>>call=SWMSAppRetroift.getInstance("updateBinState").getSWMSApi().updateBinState(bin.getBinid());

            call.enqueue(new Callback<List<Bin>>() {
                @Override
                public void onResponse(Call<List<Bin>> call, Response<List<Bin>> response) {

                    if(response.body().get(0).getBinid()>0){


                        //Toast.makeText(context,"تم تفريغ الحاوية",Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<List<Bin>> call, Throwable t) {

                }
            });
        }

        counter++;
    }
}