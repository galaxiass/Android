package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class neo_aithma extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button timeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neo_aithma);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner dropDown = (Spinner) findViewById(R.id.fusiotherapeuthrio_spinner);
        //make spinner work
        Spinner dropDown2 = (Spinner) findViewById(R.id.yphresia_spinner);
        //make spinner work

        //date picker
        initDatePicker();
        dateButton = findViewById(R.id.datepickerbutton);
        dateButton.setText(getTodaysDate());

        //time picker
        timeButton = findViewById(R.id.timepickerButton);
    }



    //making back button work
    public boolean  onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //making date selector work
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month ,year);

    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.BUTTON_NEGATIVE;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return  getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "ΙΑΝ";
        if(month == 2)
            return "ΦΕΒ";
        if(month == 3)
            return "ΜΑΡ";
        if(month == 4)
            return "ΑΠΡ";
        if(month == 5)
            return "ΜΑΙΟΣ";
        if(month == 6)
            return "ΙΟΥΝ";
        if(month == 7)
            return "ΙΟΥΛ";
        if(month == 8)
            return "ΑΥΓ";
        if(month == 9)
            return "ΣΕΠ";
        if(month == 10)
            return "ΟΚΤ";
        if(month == 11)
            return "ΝΟΒ";
        if(month == 12)
            return "ΔΕΚ";

        //default should never happen
        return "ΙΑΝ";
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    //making time selector work
    int hour, minute;
    public void popTimePicker (View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));

            }
        };

        int style = AlertDialog.BUTTON_NEGATIVE;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}
