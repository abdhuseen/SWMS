package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

public class Driver {


    @SerializedName("id")
    private int Did;//driver id

    @SerializedName("name")
    private String Dname;//driver name

    @SerializedName("ssn")
    private String Dssn; //driver ssn

    @SerializedName("birthDate")
    private String bDate;// driver birth date

    @SerializedName("salary")
    private double salary; // driver salary

    @SerializedName("address")
    private String address;// driver address

    @SerializedName("phone")
    private  String phone; //driver phone number

    @SerializedName("password")
    private String password;//driver password
    @SerializedName("driverqueryresult")
    private boolean queryResult;

    public Driver(int did, String dname, String dssn, String bDate, double salary, String address, String phone, String password) {
        Did = did;
        Dname = dname;
        Dssn = dssn;
        this.bDate = bDate;
        this.salary = salary;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int did) {
        Did = did;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getDssn() {
        return Dssn;
    }

    public void setDssn(String dssn) {
        Dssn = dssn;
    }

    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isQueryResult() {
        return queryResult;
    }


}
