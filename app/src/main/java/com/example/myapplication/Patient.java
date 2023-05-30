package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Patient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
    }


        private String name;
        private int amka;
        private String address;
        private String password;

        // Constructor
        public Patient(String name, int amka, String address, String password) {
            this.name = name;
            this.amka = amka;
            this.address = address;
            this.password = password;
        }

        // Getter and Setter methods for the attributes
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAmka() {
            return amka;
        }

        public void setAmka(int amka) {
            this.amka = amka;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

}