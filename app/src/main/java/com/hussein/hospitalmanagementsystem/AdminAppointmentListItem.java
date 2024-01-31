package com.hussein.hospitalmanagementsystem;

public class AdminAppointmentListItem {
    private String appointmentId;
    private String patientId;
    private String doctorName;
    private String schedule;
    private String payment;

    public AdminAppointmentListItem(String appointmentId, String patientId, String doctorName, String schedule, String payment) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.schedule = schedule;
        this.payment = payment;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
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
}

