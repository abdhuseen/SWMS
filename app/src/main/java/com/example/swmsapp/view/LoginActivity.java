package com.example.swmsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.swmsapp.R;
import com.example.swmsapp.controller.SWMSAppRetroift;
import com.example.swmsapp.controller.SharedPreferencesManger;
import com.example.swmsapp.controller.Valid;
import com.example.swmsapp.model.Driver;
import com.example.swmsapp.model.Truck;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    int truckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //fill drop down menu in available trucks
        Call<List<Truck>> callavailableTrucks= SWMSAppRetroift.getInstance("availableTrucks").getSWMSApi().availableTrucks();
        Spinner menu_availableTrucks=findViewById(R.id.spinner_availableTrucks);
        callavailableTrucks.enqueue(new Callback<List<Truck>>() {
            @Override
            public void onResponse(Call<List<Truck>> call, Response<List<Truck>> response) {


                List<Truck>availableTrucks=response.body();

                if(!availableTrucks.get(0).isQueryResult()){


                }

                ArrayList<Integer>id_trucks=new ArrayList<>();

                for(int index=0;index<availableTrucks.size();index++){

                    id_trucks.add(availableTrucks.get(index).getTid());
                }





                ArrayAdapter<Integer>adapter= new ArrayAdapter<Integer>(getApplicationContext(),
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,id_trucks);

                menu_availableTrucks.setAdapter(adapter);


                //get the truck id selected by the driver


              menu_availableTrucks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                      truckId=Integer.parseInt(menu_availableTrucks.getItemAtPosition(position)+"");

                  }

                  @Override
                  public void onNothingSelected(AdapterView<?> parent) {
                      truckId=Integer.parseInt(menu_availableTrucks.getItemAtPosition(0)+"");
                  }
              });




            }

            @Override
            public void onFailure(Call<List<Truck>> call, Throwable t) {

            }
        });



        if(SharedPreferencesManger.getInstance(getApplicationContext()).isLogin()){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        TextInputLayout inputLayout_driverId,inputLayout_driverPassword;
        Button login;

        inputLayout_driverId=findViewById(R.id.textField_driverId);

        inputLayout_driverPassword=findViewById(R.id.textField_driverPassword);

        login=findViewById(R.id.LoginButton);



        View.OnClickListener loginButtonClicked=(View v)->{

            String driverId=inputLayout_driverId.getEditText().getText().toString();

            String driverPassword=inputLayout_driverPassword.getEditText().getText().toString();

           if(Valid.isEmpty(driverId)){

               inputLayout_driverId.setError("حقل رقم تعريف السائق فارغ ");
           }

           else {

               inputLayout_driverId.setError("");
           }
            if(Valid.isEmpty(driverPassword)){

               inputLayout_driverPassword.setError(" حقل كلمة المرور فارغ ");
           }

           else {
                inputLayout_driverPassword.setError("");
           }

           if(!Valid.isEmpty(driverId)&&!Valid.isEmpty(driverPassword)){

               // check driver in database


               Call<List<Driver>> call= SWMSAppRetroift.getInstance("").getSWMSApi().driverLogin(Integer.parseInt(driverId),driverPassword);

               call.enqueue(new Callback<List<Driver>>() {
                   @Override
                   public void onResponse(Call<List<Driver>> call, Response<List<Driver>> response) {

                       List<Driver>drivers=response.body();

                       //boolean r=drivers.get(0).isQueryResult();

                       if(drivers.get(0).isQueryResult()){

                           //driver is in data base



                          SharedPreferencesManger.getInstance(getApplicationContext()).driverLogin(drivers.get(0),truckId);

                           Call<List<Truck>> callUpdateTruckUsed= SWMSAppRetroift.getInstance("updateTruckUsedState").getSWMSApi().updateTruckUsed(SharedPreferencesManger.getInstance(getApplicationContext()).truckIdOfDriver(),1);

                           callUpdateTruckUsed.enqueue(new Callback<List<Truck>>() {
                               @Override
                               public void onResponse(Call<List<Truck>> call, Response<List<Truck>> response) {

                                   //update truck to be used
                                   List<Truck>truck_update=response.body();

                                   //Toast.makeText(LoginActivity.this, truck_update.get(0).getTid()+"", Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onFailure(Call<List<Truck>> call, Throwable t) {

                               }
                           });

                           startActivity(new Intent(getApplicationContext(),MainActivity.class));


                       }else {

                           //driver not exist in database

                          // Toast.makeText(getApplicationContext(), "خطأ في رقم تعريف السائق او كلمة المرور ", Toast.LENGTH_LONG).show();
                       }

                   }

                   @Override
                   public void onFailure(Call<List<Driver>> call, Throwable t) {

                      Toast.makeText(LoginActivity.this, t.getMessage()+"", Toast.LENGTH_LONG).show();
                      // Toast.makeText(getApplicationContext(), "خطأ في رقم تعريف السائق او كلمة المرور ", Toast.LENGTH_LONG).show();
                   }
               });

              // Toast.makeText(this, "request db", Toast.LENGTH_SHORT).show();

           }

        };


        login.setOnClickListener(loginButtonClicked);

    }
}