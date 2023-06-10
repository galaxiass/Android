package com.example.myapplication;

public class FinancialMovement {
    private String date;
    private String office;
    private String service;
    private double price;

    public FinancialMovement(String date, String office, String service, double price) {
        this.date = date;
        this.office = office;
        this.service = service;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public String getOffice() {
        return office;
    }

    public String getService() {
        return service;
    }

    public double getPrice() {
        return price;
    }
}
