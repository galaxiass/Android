package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AddServiceActivity extends AppCompatActivity {

    private EditText editTextServiceId;
    private EditText editTextServiceName;
    private EditText editTextServiceCost;
    private EditText editTextServiceDescription;
    private Button buttonAddService;
    private Spinner spinnerClinic;
    private List<String> clinicList;
    private String selectedClinic; // Variable to hold the selected clinic

    private static final String PREF_SELECTED_CLINIC = "selected_clinic";

    private SharedPreferences sharedPreferences;

    private String url = "http://localhost/physiodate/physiodate.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Set up the Toolbar with back arrow button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        editTextServiceId = findViewById(R.id.editTextServiceId);
        editTextServiceName = findViewById(R.id.editTextServiceName);
        editTextServiceCost = findViewById(R.id.editTextServiceCost);
        editTextServiceDescription = findViewById(R.id.editTextServiceDescription);
        buttonAddService = findViewById(R.id.buttonAddService);
        spinnerClinic = findViewById(R.id.spinnerClinic);

        // Set click listener for Add Service button
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });

        // Fetch clinic data from the database and populate the clinicList
        fetchClinicData();

        // Set up the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, clinicList);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerClinic.setAdapter(adapter);

        // Set up the item selected listener for the spinner
        spinnerClinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedClinic = clinicList.get(position);
                saveSelectedClinic(selectedClinic); // Save the selected clinic to shared preferences
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restore the selected clinic to the spinner
        if (selectedClinic != null) {
            int position = clinicList.indexOf(selectedClinic);
            if (position != -1) {
                spinnerClinic.setSelection(position);
            }
        }
    }

    // Save the selected clinic to shared preferences
    private void saveSelectedClinic(String clinic) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_SELECTED_CLINIC, clinic);
        editor.apply();
    }

    // Retrieve the selected clinic from shared preferences
    private String getSelectedClinic() {
        return sharedPreferences.getString(PREF_SELECTED_CLINIC, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the back arrow button click
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fetchClinicData() {
        // TODO: Implement fetching clinic data from the database and populate clinicList
        // For the sake of this example, I'll populate clinicList with dummy data
        clinicList = new ArrayList<>();
        clinicList.add("Clinic 1");
        clinicList.add("Clinic 2");
        clinicList.add("Clinic 3");
    }

    private void addService() {
        String serviceIdText = editTextServiceId.getText().toString().trim();
        String serviceNameText = editTextServiceName.getText().toString().trim();
        String serviceCostText = editTextServiceCost.getText().toString().trim();
        String serviceDescriptionText = editTextServiceDescription.getText().toString().trim();
        String clinicText = getSelectedClinic();

        if (serviceIdText.isEmpty() || serviceNameText.isEmpty() || serviceCostText.isEmpty() || serviceDescriptionText.isEmpty() || clinicText == null) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construct the complete URL with encoded parameters
        try {
            String fullUrl = url +
                    "?serviceId=" + URLEncoder.encode(serviceIdText, "UTF-8") +
                    "&serviceName=" + URLEncoder.encode(serviceNameText, "UTF-8") +
                    "&serviceCost=" + URLEncoder.encode(serviceCostText, "UTF-8") +
                    "&serviceDescription=" + URLEncoder.encode(serviceDescriptionText, "UTF-8") +
                    "&clinic=" + URLEncoder.encode(clinicText, "UTF-8");

            // Send the request
            StringRequest stringRequest = new StringRequest(Request.Method.GET, fullUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Handle the response
                            try {
                                // Parse the response JSON
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");

                                // Display the response message
                                Toast.makeText(AddServiceActivity.this, message, Toast.LENGTH_SHORT).show();

                                // Clear the input fields if the service was added successfully
                                if (status.equals("success")) {
                                    editTextServiceId.setText("");
                                    editTextServiceName.setText("");
                                    editTextServiceCost.setText("");
                                    editTextServiceDescription.setText("");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(AddServiceActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error response
                            Toast.makeText(AddServiceActivity.this, "Error adding service", Toast.LENGTH_SHORT).show();
                        }
                    });

            // Add the request to the RequestQueue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error adding service", Toast.LENGTH_SHORT).show();
        }
    }

}
