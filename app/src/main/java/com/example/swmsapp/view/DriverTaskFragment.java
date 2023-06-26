package com.example.swmsapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.swmsapp.R;
import com.example.swmsapp.controller.SWMSAppRetroift;
import com.example.swmsapp.controller.SharedPreferencesManger;
import com.example.swmsapp.controller.TaskAdapter;
import com.example.swmsapp.model.Bin;
import com.example.swmsapp.model.DriverTask;
import com.example.swmsapp.model.Node;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DriverTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DriverTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DriverTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DriverTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DriverTaskFragment newInstance(String param1, String param2) {
        DriverTaskFragment fragment = new DriverTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView tasksList=view.findViewById(R.id.TasksListView);
        Bundle args=getArguments();

        if(args!=null&&args.getInt("flag")==0){

        double latitude=args.getDouble("lat");


        double longitude=args.getDouble("long");

        int truckId= SharedPreferencesManger.getInstance(view.getContext()).truckIdOfDriver();

            Call<List<Node>>call= SWMSAppRetroift.getInstance("rootingNodes").getSWMSApi().getRootingNodes(truckId,latitude,longitude);

            call.enqueue(new Callback<List<Node>>() {
                @Override
                public void onResponse(Call<List<Node>> call, Response<List<Node>> response) {

                    if(response.body()!=null){


                       List<Node>nodes=response.body();





                        tasksList.setAdapter(new TaskAdapter(view.getContext(),nodes));

                    }
                }

                @Override
                public void onFailure(Call<List<Node>> call, Throwable t) {

                }
            });

        }


    }
}