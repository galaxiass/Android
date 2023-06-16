package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewAppointment extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button timeButton;
    private final String myIP = "192.168.1.9";
    private Button OKbutton;
    private ClinicList cl; //clinic list
    private ClinicList sl; //service list

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        cl = new ClinicList(myIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neo_aithma);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //clinic spinner
        Spinner clinicdropDown = (Spinner) findViewById(R.id.fusiotherapeuthrio_spinner);
        ArrayAdapter<String> clinicarrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        cl.getAllClinics());
        clinicdropDown.setAdapter(clinicarrayAdapter);

        //takes chosen clinic and based on that shows available services
        String clinic = String.valueOf(clinicdropDown.getSelectedItem());

        //service spinner
        Spinner servicedropDown = (Spinner) findViewById(R.id.yphresia_spinner);
        ArrayAdapter<String> servicearrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        sl.getService(clinic));
        clinicdropDown.setAdapter(servicearrayAdapter);


        //date picker
        initDatePicker();
        dateButton = findViewById(R.id.datepickerbutton);
        dateButton.setText(getTodaysDate());

        //time picker
        timeButton = findViewById(R.id.timepickerButton);

        //OK button
        OKbutton = findViewById(R.id.OKbutton);


    }

    //making back button work
    public boolean onOptionsItemSelected(MenuItem item) {
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
        return makeDateString(day, month, year);

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
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "ΙΑΝ";
        if (month == 2)
            return "ΦΕΒ";
        if (month == 3)
            return "ΜΑΡ";
        if (month == 4)
            return "ΑΠΡ";
        if (month == 5)
            return "ΜΑΙΟΣ";
        if (month == 6)
            return "ΙΟΥΝ";
        if (month == 7)
            return "ΙΟΥΛ";
        if (month == 8)
            return "ΑΥΓ";
        if (month == 9)
            return "ΣΕΠ";
        if (month == 10)
            return "ΟΚΤ";
        if (month == 11)
            return "ΝΟΒ";
        if (month == 12)
            return "ΔΕΚ";

        //default should never happen
        return "ΙΑΝ";
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    //making time selector work
    int hour, minute;

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        int style = AlertDialog.BUTTON_NEGATIVE;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }


    //make time into time format
    public String getTime(){

        String t = String.format(Locale.getDefault(), "%02d:%02d", hour / 100, minute % 100);
        return t;
    }

    //making the ok button save the data
    public void SaveOnClick(View v) {
        Spinner clinicdropDown = (Spinner) findViewById(R.id.fusiotherapeuthrio_spinner);
        String clinic = String.valueOf(clinicdropDown.getSelectedItem());

        Spinner servicedropDown = (Spinner) findViewById(R.id.yphresia_spinner);
        String service = String.valueOf(servicedropDown.getSelectedItem());

        dateButton = findViewById(R.id.datepickerbutton);
        String date = getTodaysDate();

        timeButton = findViewById(R.id.timepickerButton);
        String chosen_time = getTime();

        Boolean CheckAllFields = false;



        if (clinic.isEmpty() ) setSpinnerError(clinicdropDown, "field can't be empty");
        else if (service.isEmpty() ) setSpinnerError(servicedropDown, "field can't be empty");
        //else if (date.isEmpty()  ) setSpinnerError(dateButton, "field can't be empty");
        //else if (chosen_time.isEmpty()) setSpinnerError(timeButton, "field can't be empty");
        else CheckAllFields = true;


        if (CheckAllFields) {
            Intent i = new Intent(NewAppointment.this, SelectScreen.class);
            startActivity(i);
        }

        OKbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://" + myIP + "/physiodate/logAppt.php?clinic=" + clinic + "&service=" + service + "&date=" + date + "&time" + chosen_time;
                try {
                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                    okHttpHandler.logAppt(url);
                    Toast.makeText(getApplicationContext(), "Selection Logged", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void setSpinnerError(Spinner spinner, String error){
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinner.performClick(); // to open the spinner list if error is found.

        }
    }
}
