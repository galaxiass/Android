import android.os.*;
import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
package com.example.myapplication;

import android.os.StrictMode;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler {

    private String url;

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String fetchPatientHistory(String patient){
        url = "http://192.168.2.2/physiodate/getHistory.php?patient=" + patient;
        String historyData = this.makeRequest(url);;

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
  }  }