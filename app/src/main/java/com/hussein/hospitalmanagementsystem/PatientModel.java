package com.hussein.hospitalmanagementsystem;

import java.io.Serializable;

public class PatientModel implements Serializable {

    private int id;
    private String name;
    private String dob;
    private String gender;
    private String bloodgroup;
    private String email;
    private String mobileNo;
    private String address;
    private String password;

    public PatientModel() {
    }

    public PatientModel(int id, String name, String dob, String gender, String bloodgroup, String email, String mobileNo, String address, String password) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.email = email;
        this.mobileNo = mobileNo;
        this.address = address;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getMobileNo() {
        return mobileNo;
    }

    public String getBloodGroup() {
        return bloodgroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodgroup = bloodGroup;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

