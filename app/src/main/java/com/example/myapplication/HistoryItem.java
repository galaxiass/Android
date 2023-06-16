package com.example.myapplication;

public class HistoryItem {
    private String date;
    private String serviceType;
    private String price;

    public HistoryItem(String date, String serviceType, String price) {
        this.date = date;
        this.serviceType = serviceType;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getPrice() {
        return price;
    }
}
