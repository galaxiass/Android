package com.example.r9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Patient> patientList;
    private PatientAdapter patientAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView = setLayoutManager(new LinearLayoutManager(this));
        patientListList = new ArrayList<>();

        patientList.add(new Patient("Δημοσθένης Καραμήτρου"));
        patientList.add(new Patient("Ασθενής2"));
        patientList.add(new Patient("Ασθενής3"));
        patientList.add(new Patient("Ασθενής4"));
    }

    private void filterList(String text) {
        List<Item> filteredList = new ArrayList<>();
        for(Item item : itemList){
            if(item.getItemName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{

        }
    }
}