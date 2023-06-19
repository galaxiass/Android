package com.example.myapplication;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class okHTTPHandler {

    private String url;

    public okHTTPHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void deleteAppointment(){
        url = "http://192.168.56.1/physiodate/appointmentNotComp.php";
        this.makeRequest(url);;

    }
    public String fetchPatientHistory(String patient){
        url = "http://192.168.2.2/physiodate/getHistory.php?patient=" + patient;
        String historyData = this.makeRequest(url);

        return historyData;
    }

    private String makeRequest(String url){
        String data;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            data = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    static ArrayList<Clinic> populateDropDown(String url) throws Exception {
        ArrayList<Clinic> cList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        //System.out.println("My Response: " + data);
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while(keys.hasNext()) {
                String clinic = keys.next();
                String service = json.get(clinic).toString();
                cList.add(new Clinic(clinic, service));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cList;
    }

    ArrayList<Patient> checkPatients(String url) throws Exception {
        ArrayList<Patient> patList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("My Response: " + data);
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while(keys.hasNext()) {
                String name = keys.next();
                String password = json.getJSONObject(name).getString("name").toString();
                patList.add(new Patient(name, password));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patList;
    }

    public void logHistory(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: " + response);
    }
    public void logAppt(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: " + response);
    }


}