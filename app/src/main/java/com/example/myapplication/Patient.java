package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Patient {

        private String name;
        private String amka;
        private String address;
        private String password;

        // Constructor
        public Patient(String name, String amka, String address, String password) {
            this.name = name;
            this.amka = amka;
            this.address = address;
            this.password = password;
        }

    public Patient(String name, String amka) {
    }

    // Getter and Setter methods for the attributes
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAmka() {
            return amka;
        }

        public void setAmka(String amka) {
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