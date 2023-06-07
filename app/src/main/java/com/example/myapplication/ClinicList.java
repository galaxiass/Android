package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class ClinicList {

    ArrayList<Clinic> cList = new ArrayList<>();

    public ClinicList(String ip) {
        String url = "http://" + ip + "/physiodate/getclinic.php";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            cList = okHttpHandler.populateDropDown(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllClinics() {
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < cList.size(); i++) {
            temp.add(cList.get(i).getName());
        }
        return temp;
    }

    public List<String> getService(String b) {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<cList.size(); i++) {
            if (cList.get(i).hasName(b)) {
                temp = cList.get(i).getService();
            }
        }
        return temp;
    }
}
