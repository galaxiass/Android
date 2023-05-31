package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;

public class select_screen extends AppCompatActivity {

    Button neo_aithma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_screen);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //to make the back button functional
       /* public boolean  onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }

        public boolean onCreateOptionsMenu(Menu menu) {
            return true;
        }*/

        //NEO AITHMA functional
        neo_aithma = findViewById(R.id.neo_aithma);

        neo_aithma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(select_screen.this, neo_aithma.class);
                startActivity(intent);
            }
        });
    }

}