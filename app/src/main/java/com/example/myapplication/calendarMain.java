package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class calendarMain extends AppCompatActivity implements CalendarAdapter.OnItemListener {
        private TextView monthYearText;
        private RecyclerView calendarRecyclerView;
        private LocalDate selectedDate;
        private Button backButton;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_calendar_main);
                initWidgets();
                selectedDate = LocalDate.now();
                setMonthView();

                backButton = findViewById(R.id.button); // Initialize the back button
                backButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                onBackPressed(); // Handle the back button click event
                        }
                });
        }


        private void initWidgets() {
                calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
                monthYearText = findViewById(R.id.monthYearTV);
        }

        private void setMonthView() {
                monthYearText.setText(monthYearFromDate(selectedDate));
                ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

                CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
                calendarRecyclerView.setLayoutManager(layoutManager);
                calendarRecyclerView.setAdapter(calendarAdapter);
        }



        private ArrayList<String> daysInMonthArray(LocalDate date) {
                ArrayList<String> daysInMonthArray = new ArrayList<>();
                YearMonth yearMonth = YearMonth.from(date);

                int daysInMonth = yearMonth.lengthOfMonth();

                LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
                int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

                for (int i = 1; i <= 42; i++) {
                        if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                                daysInMonthArray.add("");
                        } else {
                                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
                        }
                }
                return daysInMonthArray;
        }


        private String monthYearFromDate(LocalDate date) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
                return date.format(formatter);
        }


        public void previousMonthAction(View view) {
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
        }


        public void nextMonthAction(View view) {
                selectedDate = selectedDate.plusMonths(1);
                setMonthView();
        }


        @Override
        public void onItemClick(int position, String dayText) {
                if (!dayText.equals("")) {
                        String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                        // Show the pop-up window with the selected date
                        showPopupWindow(selectedDate);
                }
        }

        @Override
        public void onBackPressed() {
                // Perform the desired action when the back button is clicked
                // For example, you can show a confirmation dialog or navigate back to the previous activity
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to go back?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                       calendarMain.super.onBackPressed(); // Navigate back to the previous activity
                                }
                        })
                        .setNegativeButton("No", null)
                        .show();
        }

        private void showPopupWindow(LocalDate selectedDate) {
                // Create an AlertDialog.Builder object
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                // Get the layout inflater
                LayoutInflater inflater = LayoutInflater.from(this);
                View popupView = inflater.inflate(R.layout.popup_layout, null);

                String url = "http://your_local_ip/physiodate/your_php_file.php" + selectedDate.toString();
                String responseData = makeRequest(url);

                // Assuming the response data is a JSON string, you can parse it and extract the relevant information
                // For example, if the response data contains a "title" and "description" field:
                String title = "";
                String description = "";
                try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        title = jsonObject.getString("title");
                        description = jsonObject.getString("description");
                } catch (JSONException e) {
                        e.printStackTrace();
                }

                // Find the views in the pop-up layout
                TextView titleTextView = popupView.findViewById(R.id.titleTextView);
                TextView descriptionTextView = popupView.findViewById(R.id.descriptionTextView);


                // Populate the views with the fetched data
                titleTextView.setText(title);
                descriptionTextView.setText(description);


                // Set the pop-up view to the dialog builder
                dialogBuilder.setView(popupView);

                // Create the AlertDialog object
                AlertDialog alertDialog = dialogBuilder.create();

                // Show the pop-up window
                alertDialog.show();

        }

        public String makeRequest(String url) {
                new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                                String data;
                                OkHttpClient client = new OkHttpClient().newBuilder().build();
                                RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
                                Request request = new Request.Builder().url(params[0]).method("POST", body).build();
                                Response response = null;
                                try {
                                        response = client.newCall(request).execute();
                                        data = response.body().string();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        data = null;
                                }
                                return data;
                        }

                        @Override
                        protected void onPostExecute(String result) {
                                if (result != null) {
                                        // Process the response data here
                                        // Update UI or perform any necessary actions
                                } else {
                                        // Handle error case
                                }
                        }
                }.execute(url);
                return url;
        }

}