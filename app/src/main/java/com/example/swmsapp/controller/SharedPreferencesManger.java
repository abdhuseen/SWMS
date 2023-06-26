package com.example.swmsapp.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.swmsapp.model.Driver;
import com.example.swmsapp.view.LoginActivity;

public class SharedPreferencesManger {

    //store user data when it login to do not ask driver to login again
    private static final String SHARED_PREF_NAME="SWMSSharedPref";
    private static final String KEY_ID="KeyId";
    private static final String KEY_NAME="KeyUsername";
    private static final String KEY_PASSWORD="KeyPassword";
    private static final String KEY_PHONE="KeyPhone";
    private static final String KEY_SSN="KeySSN";
    private static final String KEY_BDATE="BDATE";//BIRTH DATE
    private static final String KEY_SALARY="salary";
    private static final String KEY_ADDRESS="ADDRESS";
    private static final String KEY_TruckId="0";
    private static final String ENABLE_FOR_ROUTING="-1";
    private static final String END_TRIP="END";
    private static SharedPreferencesManger instance;
    private static Context context;

    public boolean getEndTrip() {

        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("END",true);
    }

    private SharedPreferencesManger(Context context){
        SharedPreferencesManger.context=context;
    }
    public static synchronized SharedPreferencesManger getInstance(Context context){
        if(instance==null)
            instance=new SharedPreferencesManger(context);

        return instance;
    }


    public void driverLogin(Driver driver,int truckId){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(KEY_ID,driver.getDid());
        editor.putString(KEY_NAME,driver.getDname());
        editor.putString(KEY_PASSWORD,driver.getPassword());
        editor.putString(KEY_PHONE,driver.getPhone());
        editor.putString(KEY_SSN,driver.getDssn());
        editor.putString(KEY_BDATE,driver.getbDate());
        editor.putString(KEY_SALARY,driver.getSalary()+"");
        editor.putString(KEY_ADDRESS,driver.getAddress());
        editor.putInt(KEY_TruckId,truckId);
        editor.putInt(ENABLE_FOR_ROUTING,1);//enabled for routing
        editor.apply();
    }

    public boolean isLogin(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ID,0)!=0;
    }
    public void logOut(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    public void driverUpdate(Driver driver,int truckId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID,driver.getDid());
        editor.putString(KEY_NAME,driver.getDname());
        editor.putString(KEY_PASSWORD,driver.getPassword());
        editor.putString(KEY_PHONE,driver.getPhone());
        editor.putString(KEY_SSN,driver.getDssn());
        editor.putString(KEY_BDATE,driver.getbDate());
        editor.putString(KEY_SALARY,driver.getSalary()+"");
        editor.putString(KEY_ADDRESS,driver.getAddress());
        editor.putInt(KEY_TruckId,truckId);
        editor.apply();
    }

    public Driver getDrivers()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  new Driver(
                sharedPreferences.getInt(KEY_ID,0),
                sharedPreferences.getString(KEY_NAME,"driver"),
                sharedPreferences.getString(KEY_SSN,""),
                sharedPreferences.getString(KEY_BDATE,""),
                Double.parseDouble(sharedPreferences.getString(KEY_SALARY,"00")),
                sharedPreferences.getString(KEY_ADDRESS,""),
                sharedPreferences.getString(KEY_PHONE,"00962"),
                sharedPreferences.getString(KEY_PASSWORD,"******")

        );

    }

    public int truckIdOfDriver(){

        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_TruckId,0);
    }

    public int getEnable(){

        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(ENABLE_FOR_ROUTING,1);
    }


    public void setEnabled(int val){

        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(ENABLE_FOR_ROUTING,val);//enabled for routing
        editor.apply();


    }


    public void setEndTrip(boolean b){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("END",b);
        editor.apply();
    }


}
