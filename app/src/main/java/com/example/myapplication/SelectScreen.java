package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SelectScreen extends AppCompatActivity {

    Button neo_aithma;
    Button activity_financial_movements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_screen);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //NEO AITHMA functional
        neo_aithma = findViewById(R.id.neo_aithma);

        neo_aithma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(SelectScreen.this, NewAppointment.class);
                startActivity(intent);
            }
        });

        activity_financial_movements = findViewById(R.id.oikon_katastash);
        activity_financial_movements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(SelectScreen.this, FinancialMovementsActivity.class);
                startActivity(intent);
            }
        });
    }
    //to make the back button functional
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