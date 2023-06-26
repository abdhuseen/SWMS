package com.example.swmsapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swmsapp.R;
import com.example.swmsapp.model.Bin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BinAdapter extends BaseAdapter {

    Context context;
    List<Bin> filledBins;
    LayoutInflater inflater;
    public BinAdapter(Context context, List<Bin> filledBins) {
        this.context = context;
        this.filledBins = filledBins;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return filledBins.size();
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

        convertView=inflater.inflate(R.layout.filled_bins_list,parent,false);

        TextView showBinID=convertView.findViewById(R.id.bin_id_from_task);

        TextView showBinState=convertView.findViewById(R.id.bin_state_from_task);

        showBinID.setText(filledBins.get(position).getBinid()+"");

        int state=filledBins.get(position).getState();

        if(state==1){

            showBinState.setText("نصف ممتلئ");
        }else {

            showBinState.setText(" ممتلئ");
        }



        return convertView;
    }
}
