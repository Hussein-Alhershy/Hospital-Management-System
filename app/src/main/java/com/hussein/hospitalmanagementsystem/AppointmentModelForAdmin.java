package com.hussein.hospitalmanagementsystem;

import java.io.Serializable;

public class AppointmentModelForAdmin implements Serializable {

    private long appointmentId;
    private long patientId;
    private String doctorName;
    private String schedule;
    private String status;
    private String payment;


    public AppointmentModelForAdmin() {

    }


    public AppointmentModelForAdmin(long appointmentId, long patientId, String doctorName, String schedule, String payment) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.schedule = schedule;
        this.payment = payment;
    }


    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
