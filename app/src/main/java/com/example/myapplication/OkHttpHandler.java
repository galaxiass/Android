package com.example.myapplication;
import android.os.*;
import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import okhttp3.*;


/* 192.168.56.1*/
public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    ArrayList<Patient> populatePatients(String url) throws Exception {
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
                String amka = keys.next();
                String name = json.getJSONObject(amka).getString("name").toString();
                String address = json.getJSONObject(amka).getString("address").toString();
                String password = json.getJSONObject(amka).getString("password").toString();
                patList.add(new Patient(name, amka, address,password));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patList;
    }

    public void getMedia(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: " + response);
  }  }
