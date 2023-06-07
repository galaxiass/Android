package com.example.myapplication;

import androidx.appcompat.app.*;

import android.content.Intent;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.squareup.picasso.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button patientButton = findViewById(R.id.patientButton);
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Sign In Activity for the patient
                Intent intent = new Intent(MainActivity.this, PatientSignIn.class);
                startActivity(intent);
            }
        });
    }
}

