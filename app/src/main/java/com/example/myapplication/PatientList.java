package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class PatientList {
    private List<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public Patient findPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }
}
