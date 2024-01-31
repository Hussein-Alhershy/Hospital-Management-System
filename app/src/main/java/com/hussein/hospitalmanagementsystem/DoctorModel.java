package com.hussein.hospitalmanagementsystem;

import java.io.Serializable;

public class DoctorModel implements Serializable {

    private int id;
    private String name;
    private String dob;
    private String gender;
    private String email;
    private String mobileNo;
    private String language;
    private String specialization;
    private String experience;
    private String schedule;
    private String password;
    private int price;

    public DoctorModel() {

    }

    public DoctorModel(int id, String name, String dob, String gender, String schedule, String language, String experience, String specialization, String email, String mobileNo, String password, int price) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.mobileNo = mobileNo;
        this.language = language;
        this.experience = experience;
        this.specialization = specialization;
        this.schedule = schedule;
        this.password = password;
        this.price = price;
    }

    public DoctorModel(int id, String name, String dob, String gender, String schedule, String language, String experience, String specialization, String email, String mobileNo, String password) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.mobileNo = mobileNo;
        this.language = language;
        this.experience = experience;
        this.specialization = specialization;
        this.schedule = schedule;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
