package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class PatientSignUp extends AppCompatActivity {

    private final String myIP = "192.168.56.1";

    private PatientList patientList; // Instance of the PatientList class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);
        patientList = new PatientList();
        EditText nameSignUp=findViewById(R.id.nameSignUp);
        EditText passwordSignUp=findViewById(R.id.passwordSignUp);
        EditText addressSignUp=findViewById(R.id.addressSignUp);
        EditText amkaSignUp=findViewById(R.id.amkaSignUp);

        Button signUpButton = findViewById(R.id.okButton);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameSignUp.getText().toString();
                String amka = amkaSignUp.getText().toString();
                String address = addressSignUp.getText().toString();
                String password = passwordSignUp.getText().toString();

                // Check if the patient already exists
                Patient existingPatient = patientList.findPatientByName(name);
                if (existingPatient != null) {
                    Toast.makeText(PatientSignUp.this, "Patient already exists", Toast.LENGTH_SHORT).show();
                } else {
                    // Create new patient account
                    Patient newPatient = new Patient(name, amka, address, password);

                    // Add the new patient account to the patientList
                    patientList.addPatient(newPatient);

                    // Store the patient account information in the database
                    String url= "http://"+myIP+"/physiodate/getMedia.php?name=" + nameSignUp.getText() + "&address=" + addressSignUp.getText() + "&password=" + passwordSignUp+"&amka" + amkaSignUp;
                    try {
                        OkHttpHandler okHttpHandler = new OkHttpHandler();
                        okHttpHandler.getMedia(url);
                        Toast.makeText(getApplicationContext(), "Selection Logged", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(PatientSignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

                    // back to the Sign In Activity
                    Intent intent = new Intent(PatientSignUp.this, PatientSignIn.class);
                    startActivity(intent);
                    finish(); //  to prevent going back to it
                }
            }
        });
    }
}