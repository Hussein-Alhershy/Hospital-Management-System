package com.hussein.hospitalmanagementsystem;

public class DoctorListItemModel {
    private String name;
    private String specialization;
    private String schedule;
    private  int price;

    public DoctorListItemModel(String name, String specialization, String schedule, int price) {
        this.name = name;
        this.specialization = specialization;
        this.schedule = schedule;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getPrice() { return price; }
}

