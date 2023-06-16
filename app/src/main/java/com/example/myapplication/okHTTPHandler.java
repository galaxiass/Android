package com.example.myapplication;

import android.os.StrictMode;

import java.io.IOException;

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

}