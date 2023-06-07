package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PatientHistory extends AppCompatActivity {

    String date;
    String serviceType;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView listView = findViewById(R.id.historyListView);
        TextView name = (TextView) findViewById(R.id.patientName);
        TextView noData = (TextView) findViewById(R.id.noDataText);
        Intent intent = getIntent();

        OkHttpHandler requestHandler = new OkHttpHandler();

        String patient =intent.getStringExtra("patientName");
        name.setText(patient);
        noData.setVisibility(TextView.GONE);
        String jsonData = requestHandler.fetchPatientHistory(patient);
        JSONObject json = null;
        try {
            json = new JSONObject(jsonData);
        } catch (JSONException e) {
            noData.setVisibility(TextView.VISIBLE);
            return;
        }
        ArrayList<HistoryItem> itemList = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            JSONObject jsonItem = null;
            try {
                jsonItem = json.getJSONObject("appointmentId" + i);
                date = jsonItem.getString("date");
                serviceType = jsonItem.getString("serviceType");
                price = jsonItem.getString("price");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            HistoryItem item = new HistoryItem(date, serviceType, price);
            itemList.add(item);
        }
        CustomAdapter adapter = new CustomAdapter(this, itemList);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

class CustomAdapter extends ArrayAdapter<HistoryItem> {

    public CustomAdapter(Context context, ArrayList<HistoryItem> items) {
        super(context, 0, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        TextView serviceTypeTextView = convertView.findViewById(R.id.serviceTypeTextView);
        TextView priceTextView = convertView.findViewById(R.id.priceTextView);

        dateTextView.setText("Date: " + item.getDate());
        serviceTypeTextView.setText("Service Type: " + item.getServiceType());
        priceTextView.setText("Price: " + item.getPrice());

        return convertView;
    }
}
