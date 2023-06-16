package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class activity_confirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //When the button 'CANCELLED' is pressed a method is called
        //to delete the appointment in question from the DB
        Button buttonUNCMPL = (Button) findViewById(R.id.buttonUncompleted);
        buttonUNCMPL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                okHTTPHandler handler = new okHTTPHandler();
                handler.deleteAppointment();
                finish();
            }
        });

        //When the button 'COMPLETED' is pressed the user
        //returns to the previous screen
        Button buttonCMPL = (Button) findViewById(R.id.buttonCompleted);
        buttonCMPL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}