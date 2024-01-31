package com.hussein.hospitalmanagementsystem;

import android.database.Cursor;

public class AppointmentModel {

    private long id;
    private long patientId;

    private String doctorName;
    private String schedule;
    private String payment;


    public AppointmentModel(long id, String doctorName, String schedule, String payment) {
        this.id = id;

        this.doctorName = doctorName;
        this.schedule = schedule;
        this.payment = payment;
    }





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment){ this.payment = payment;}
}

