package com.example.swmsapp.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swmsapp.R;
import com.example.swmsapp.controller.SWMSAppRetroift;
import com.example.swmsapp.controller.SharedPreferencesManger;
import com.example.swmsapp.model.Trip;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int driverId= SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getDid();
        TextView showTitle=view.findViewById(R.id.textView2);

        Call<List<Trip>>call= SWMSAppRetroift.getInstance("driverTrips").getSWMSApi().getDriverTrips(driverId);

        call.enqueue(new Callback<List<Trip>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                List<Trip>driverTrips=response.body();

                if(driverTrips.get(0).isTripqueryresult()==false){

                    showTitle.setText("لا يوجد احصائيات ");

                }else {

                    showTitle.setText("احصائياتي ");
                    ArrayList<String>tripsTime=new ArrayList<>();

                    ArrayList<Double>tripsDistance=new ArrayList<>();
                    // Parse the string into LocalTime using a DateTimeFormatter
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    for(Trip trip :driverTrips){

                        tripsDistance.add(trip.getDistance());



                        Duration duration= Duration.between(LocalTime.parse(correctTime(trip.getStartTime().trim()),formatter),LocalTime.parse(correctTime(trip.getEndTime().trim()),formatter));

                        tripsTime.add(duration.toMinutes()+"");


                    }


                    //filled the table of minutes and distance

                    TableLayout tableLayout = view.findViewById(R.id.tableLayout);

                    for (int i = 0; i < tripsTime.size(); i++) {
                        String minutes = tripsTime.get(i);
                        double distance = tripsDistance.get(i);

                        TableRow tableRow = new TableRow(getContext());
                        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                                TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.WRAP_CONTENT
                        ));

                        TextView minutesTextView =createTextView(String.valueOf(minutes));
                        TextView distanceTextView =createTextView(String.valueOf(distance));
                        tableRow.addView(distanceTextView);
                        tableRow.addView(minutesTextView);


                        tableLayout.addView(tableRow);


                    }


                    // draw as chart

                    LineChart lineChart = view.findViewById(R.id.lineChart);

                    List<Entry> entries = new ArrayList<>();



                    for (int i = 0; i < tripsTime.size(); i++) {
                        entries.add(new Entry(i,tripsDistance.get(i).floatValue() ));
                    }

                    LineDataSet dataSet = new LineDataSet(entries, "المسافة");
                    dataSet.setColor(Color.BLUE);
                    dataSet.setCircleColor(Color.BLUE);
                    dataSet.setLineWidth(2f);
                    dataSet.setCircleRadius(4f);
                    dataSet.setDrawCircleHole(false);

                    LineData lineData = new LineData(dataSet);
                    lineChart.setData(lineData);

                    lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(tripsTime));
                    lineChart.getXAxis().setLabelCount(tripsTime.size(), true);
                    lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                    lineChart.getXAxis().setGranularity(1f);
                    lineChart.getXAxis().setGranularityEnabled(true);

                    lineChart.getAxisLeft().setAxisMinimum(0f); // Start the Y-axis from zero

                    lineChart.getDescription().setEnabled(false);
                    lineChart.invalidate();


                    CalendarView calendarView=view.findViewById(R.id.calendarView);

                    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView v, int year, int month, int dayOfMonth) {

                            //fill table of months and number of trips
                            TableLayout tableLayout2 = view.findViewById(R.id.tableLayout2);

                            if(tableLayout2.getChildCount()>2){

                                tableLayout2.removeViews(1,tableLayout2.getChildCount()-1);
                            }
                            //get number of trips for each month in a year
                            String y=String.valueOf(year);

                          ArrayList<String>months=new ArrayList<>();

                          ArrayList<Integer>numberOfTrips=new ArrayList<>();





                          for(int i=1;i<13;i++){
                              //each month in year




                              months.add(i+"");
                              numberOfTrips.add(numberOfTrips(y,String.valueOf(i),driverTrips));

                          }








                            for (int i = 0; i < months.size(); i++) {
                                String m= months.get(i);
                                int number_trips = numberOfTrips.get(i);

                                TableRow tableRow = new TableRow(view.getContext());
                                tableRow.setLayoutParams(new TableLayout.LayoutParams(
                                        TableLayout.LayoutParams.MATCH_PARENT,
                                        TableLayout.LayoutParams.WRAP_CONTENT
                                ));

                                TextView monthsTextView =createTextView(String.valueOf(m));
                                TextView numberOfTripsTextView =createTextView(String.valueOf(number_trips));
                                tableRow.addView(numberOfTripsTextView);
                                tableRow.addView(monthsTextView);
                                tableLayout2.addView(tableRow);

                            }



                            // draw as chart2

                            LineChart lineChart2 = view.findViewById(R.id.lineChart2);

                            List<Entry> entries2 = new ArrayList<>();



                            for (int i = 0; i < months.size(); i++) {
                                entries2.add(new Entry(i,numberOfTrips.get(i).floatValue() ));
                            }

                            LineDataSet dataSet2 = new LineDataSet(entries2, "عدد الرحلات");
                            dataSet2.setColor(Color.RED);
                            dataSet2.setCircleColor(Color.RED);
                            dataSet2.setLineWidth(2f);
                            dataSet2.setCircleRadius(4f);
                            dataSet2.setDrawCircleHole(false);

                            LineData lineData2 = new LineData(dataSet2);
                            lineChart2.setData(lineData2);

                            lineChart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(months));
                            lineChart2.getXAxis().setLabelCount(months.size(), true);
                            lineChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                            lineChart2.getXAxis().setGranularity(1f);
                            lineChart2.getXAxis().setGranularityEnabled(true);

                            lineChart2.getAxisLeft().setAxisMinimum(0f); // Start the Y-axis from zero

                            lineChart2.getDescription().setEnabled(false);
                            lineChart2.invalidate();





                            AppCompatActivity activity = (AppCompatActivity)v.getContext();
                            activity.findViewById(R.id.bottom_navigation).setVisibility(View.GONE);







                        }
                    });





                }




            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

            }
        });


    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(requireContext());
        textView.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(2, 2, 2, 2);
        return textView;
    }


    private  int numberOfTrips(String year,String month,List<Trip>driverTrips){

        int count=0;
        for(Trip trip:driverTrips){
            String date[]=trip.getDate().split("-");
            if(year.equals(date[0])&&month.equals(date[1])){

                count++;
            }
        }





        return  count;
    }

    private  String  correctTime(String time){

        String correctTime="";

        String tempTime[]=time.split(":");

        String hours,minutes,seconds;

        hours=tempTime[0];

        minutes=tempTime[1];

        seconds=tempTime[2];

        if(hours.length()<2){

            hours="0"+hours;
        }

        if(minutes.length()<2){

            minutes="0"+minutes;
        }

        if(seconds.length()<2){

            seconds="0"+seconds;
        }

        correctTime=hours+":"+minutes+":"+seconds;

        return correctTime;


    }

}