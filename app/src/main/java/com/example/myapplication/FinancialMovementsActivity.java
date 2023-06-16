package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class FinancialMovementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_movements);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Sample financial movements data
        ArrayList<FinancialMovement> financialMovements = new ArrayList<>();
        financialMovements.add(new FinancialMovement("2023-06-01", "Office 1", "Service 1", 100.0));
        financialMovements.add(new FinancialMovement("2023-06-05", "Office 2", "Service 2", 75.0));
        financialMovements.add(new FinancialMovement("2023-06-08", "Office 3", "Service 3", 50.0));

        // Set up the ListView
        ListView listView = findViewById(R.id.lvFinancialMovements);
        ArrayAdapter<FinancialMovement> adapter = new ArrayAdapter<FinancialMovement>(this, android.R.layout.simple_list_item_1, financialMovements) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_financial_movement, parent, false);
                }

                FinancialMovement movement = getItem(position);

                TextView textViewDate = convertView.findViewById(R.id.textViewDate);
                TextView textViewOffice = convertView.findViewById(R.id.textViewOffice);
                TextView textViewService = convertView.findViewById(R.id.textViewService);
                TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);

                textViewDate.setText("Ημερομηνία: " + movement.getDate());
                textViewOffice.setText("Ιατρείο: " + movement.getOffice());
                textViewService.setText("Υπηρεσία: " + movement.getService());
                textViewPrice.setText("Πληρωτέο Ποσό: " + movement.getPrice());

                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle toolbar item clicks here
        if (id == android.R.id.home) {
            // Handle back button click
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
