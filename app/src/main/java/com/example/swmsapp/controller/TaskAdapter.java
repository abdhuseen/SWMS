package com.example.swmsapp.controller;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.example.swmsapp.R;
import com.example.swmsapp.model.Bin;
import com.example.swmsapp.model.DriverTask;
import com.example.swmsapp.model.Node;
import com.example.swmsapp.model.Trip;
import com.example.swmsapp.view.DriverTaskFragment;
import com.example.swmsapp.view.TripFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskAdapter extends BaseAdapter {

    Context context;
    List<Node> driverTasks;
    LayoutInflater inflater;
    public TaskAdapter(Context context, List<Node> driverTasks) {
        this.context = context;
        this.driverTasks = driverTasks;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return driverTasks.size();
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

        convertView=inflater.inflate(R.layout.daliy_tasks_list,parent,false);

        TextView description=convertView.findViewById(R.id.task_description);

        TableLayout binsTable=convertView.findViewById(R.id.tableLayout_from_task);
        description.setText(" شارع  "+driverTasks.get(position).getNstreet());

            Call<List<Bin>> call = SWMSAppRetroift.getInstance("filledBins").getSWMSApi().getFilledBinsAtNode(driverTasks.get(position).getNlatitude(),driverTasks.get(position).getNlongitude());

            call.enqueue(new Callback<List<Bin>>() {
                @Override
                public void onResponse(Call<List<Bin>> call, Response<List<Bin>> response) {

                    //response filed bins at a node

                        for (int i = 0; i < response.body().size(); i++) {
                            TableRow tableRow = new TableRow(context);
                            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                                    TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT
                            ));

                            TextView binStateTextView;
                            TextView binIdTextView =createTextView(response.body().get(i).getBinid()+"");
                            if(response.body().get(i).getState()==1){

                                binStateTextView =createTextView("نصف ممتلئة");

                            }else {

                                binStateTextView =createTextView(" ممتلئة");
                            }

                            tableRow.addView(binStateTextView);
                            tableRow.addView(binIdTextView);


                            binsTable.addView(tableRow);

                            binsTable.clearDisappearingChildren();


                        }






                }

                @Override
                public void onFailure(Call<List<Bin>> call, Throwable t) {

                }
            });














        return convertView;
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(2, 2, 2, 2);
        return textView;
    }

}
