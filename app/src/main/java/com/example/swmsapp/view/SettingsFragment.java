package com.example.swmsapp.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.swmsapp.R;
import com.example.swmsapp.controller.SWMSAppRetroift;
import com.example.swmsapp.controller.SharedPreferencesManger;
import com.example.swmsapp.controller.Valid;
import com.example.swmsapp.model.Driver;
import com.example.swmsapp.model.Truck;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment {



    public SettingsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int driverId= SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getDid();

        double driverSalary=SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getSalary();

        String driverPassword=SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getPassword();

        Switch enable=view.findViewById(R.id.switch1);

        int isEnable=SharedPreferencesManger.getInstance(view.getContext()).getEnable();

        if(isEnable==1){

            enable.setChecked(true);
        }else {

            enable.setChecked(false);
        }

      enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

              if(isChecked){

                  SharedPreferencesManger.getInstance(view.getContext()).setEnabled(1);
              }else {

                  SharedPreferencesManger.getInstance(view.getContext()).setEnabled(0);
              }
          }
      });

        MaterialTextView showDriverId=view.findViewById(R.id.show_driver_id);

        TextInputLayout driverName,driverSSn,driverBirthDate,driverCity,driverPhone;

        driverName=view.findViewById(R.id.textField_show_driverName);

        driverSSn=view.findViewById(R.id.textField_show_driverssn);

        driverBirthDate=view.findViewById(R.id.textField_show_driverDateOfBirth);


        driverCity=view.findViewById(R.id.textField_show_driverCity);

        driverPhone=view.findViewById(R.id.textField_show_driverPhone);


        //fill driver data

        showDriverId.setText(SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getDid()+"");

        driverName.getEditText().setText(SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getDname()+"");

        driverSSn.getEditText().setText(SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getDssn()+"");

        driverBirthDate.getEditText().setText(SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getbDate()+"");

        driverCity.getEditText().setText(SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getAddress()+"");

        driverPhone.getEditText().setText(SharedPreferencesManger.getInstance(view.getContext()).getDrivers().getPhone()+"");



        Button updateDriverDate=view.findViewById(R.id.updateDriverDataBtn);

        updateDriverDate.setOnClickListener(new View.OnClickListener() {

            // update driver data
            @Override
            public void onClick(View v) {

                String dname,dSSN,dBDate,dCity,dPhone;

                dname=driverName.getEditText().getText()+"";

                dSSN=driverSSn.getEditText().getText()+"";

                dBDate=driverBirthDate.getEditText().getText()+"";

                dCity=driverCity.getEditText().getText()+"";

                dPhone=driverPhone.getEditText().getText().toString();


                if(Valid.isEmpty(dname)){

                    driverName.setError("حقل الاسم فارغ");

                }else {

                    driverName.setError("");
                }

                if(Valid.isEmpty(dSSN)){

                    driverSSn.setError("حقل الرقم الوطني فارغ");
                }else {

                    if(dSSN.length()!=16){

                        driverSSn.setError("يجب ان يتكون الرقم الوطني من 16 حرف");
                    }else {

                        driverSSn.setError("");
                    }


                }



                if(Valid.isEmpty(dBDate)){

                    driverBirthDate.setError("حقل تاريخ الميلاد فارغ");
                }else {

                    driverBirthDate.setError("");
                }

                if(Valid.isEmpty(dCity)){

                    driverCity.setError("حقل المدينة فارغ");
                }else {

                    driverCity.setError("");
                }


                if(Valid.isEmpty(dPhone)){

                    driverPhone.setError("حقل رقم الهاتف فارغ");
                }else {

                    driverPhone.setError("");
                }


              if(!Valid.isEmpty(dname)&&!Valid.isEmpty(dSSN)&&!Valid.isEmpty(dBDate)
              &&!Valid.isEmpty(dCity)&&!Valid.isEmpty(dPhone)){


                  //request api of update



                  Call<List<Driver>>call= SWMSAppRetroift.getInstance("updateDriverData").getSWMSApi().updateDriverData(
                          driverId,
                          dname,
                          dSSN,
                          dBDate,
                          driverSalary,
                          dCity,
                          dPhone,
                          driverPassword);

              call.enqueue(new Callback<List<Driver>>() {
                  @Override
                  public void onResponse(Call<List<Driver>> call, Response<List<Driver>> response) {
                      List<Driver>driverList=response.body();


                      if(driverList.get(0).isQueryResult()){

                          SharedPreferencesManger.getInstance(view.getContext()).driverUpdate((Driver) driverList,
                                  SharedPreferencesManger.getInstance(view.getContext()).truckIdOfDriver());
                          Toast.makeText(view.getContext(), "تم تعديل البيانات بنجاح", Toast.LENGTH_SHORT).show();

                          startActivity(new Intent(view.getContext(),LoginActivity.class));

                      }
                  }

                  @Override
                  public void onFailure(Call<List<Driver>> call, Throwable t) {

                  }
              });



              }


            }
        });




    }
}