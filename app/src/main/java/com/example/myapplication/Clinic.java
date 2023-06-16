package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clinic {
    private String name;

    private List<String> service = new ArrayList<String>();

    public Clinic(String clinic, String service){
        name= clinic;
        this.service = Arrays.asList(service.split(","));
    }


    public String getName() {
        return name;
    }

    public boolean hasName(String b) {
        return name.equals(b);
    }

    public List<String> getService() {return service;}
}