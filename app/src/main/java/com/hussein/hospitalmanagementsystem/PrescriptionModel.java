package com.hussein.hospitalmanagementsystem;

public class PrescriptionModel {

    private int prescriptionId;
    private int appointmentId;
    private int patientId;
    private String prescription;
    private String description;
    private String advice;
    private String drName;

    public PrescriptionModel() {
    }

    public PrescriptionModel(int prescriptionId, int appointmentId, int patientId, String prescription, String description, String advice, String drName) {
        this.prescriptionId = prescriptionId;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.prescription = prescription;
        this.description = description;
        this.advice = advice;
        this.drName = drName;
    }


    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getDrName() {
        return drName;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
}

