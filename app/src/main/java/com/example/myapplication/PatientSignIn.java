package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.namespace.R;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PatientSignIn extends AppCompatActivity {

    private final String myIP = "192.168.56.1";
    private PatientList patientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        patientList = new PatientList();

        EditText nameSignIn=findViewById(R.id.nameSignIn);
        EditText passwordsignIn=findViewById(R.id.passwordSignIn);
        Button signInButton=findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameSignIn.getText().toString();
                String password = passwordsignIn.getText().toString();


                String url= "http://"+myIP+"/physiodate/getMedia.php?name=" + nameSignIn.getText() + "&password=" + passwordsignIn.getText();
                try {
                    okHTTPHandler OkHttpHandler = new okHTTPHandler();
                    OkHttpHandler.checkPatients(url);
                    Toast.makeText(getApplicationContext(), "Selection Logged", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Patient patient = patientList.findPatientByName(name);
                if (patient != null && patient.getPassword().equals(password)) {
                    Toast.makeText(PatientSignIn.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PatientSignIn.this, "Invalid name or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Sign Up Activity
                Intent intent = new Intent(PatientSignIn.this, PatientSignUp.class);
                startActivity(intent);
            }
        });

    }


    public boolean  onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}