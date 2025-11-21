package com.example.carmanagement.models;

public class Car {
    private String id;
    private String model;
    private String make;
    private int year;

    public  Car() {
    }
    public Car(String id, String model, String make, int year) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }
}
