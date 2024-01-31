package com.hussein.hospitalmanagementsystem;

import java.io.Serializable;

public class AppointmentModelForDoctor implements Serializable {
    private int appointmentId;
    private int patientId;
    private String schedule;

    public AppointmentModelForDoctor() {

    }

    public AppointmentModelForDoctor(int appointmentId, int patientId, String schedule) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.schedule = schedule;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}

