package com.example.android.xxiang1_sizebook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.math.RoundingMode;
import java.util.Calendar;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.lang.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddPerson extends AppCompatActivity {

    String FILENAME = "people.txt";

    Person person = new Person();

    private ArrayList<Person> PersonList = new ArrayList<>();

    private Calendar addDate = Calendar.getInstance();

    private EditText nameEditText;
    private EditText dateEditText;
    private EditText neckEditText;
    private EditText bustEditText;
    private EditText chestEditText;
    private EditText waistEditText;
    private EditText hipEditText;
    private EditText inseamEditText;
    private EditText commentEditText;
    private Button doneButton;

    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        // Find the views that we will need to read user input from
        nameEditText= (EditText) findViewById(R.id.name_view);
        dateEditText = (EditText) findViewById(R.id.date_view);

        dateEditText.setFocusable(false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        dateEditText.setHint("Date (Default: " + sdf.format(addDate.getTime()) + ")");

//source: Android - Date Picker
// https://www.tutorialspoint.com/android/android_datepicker_control.html
        year = addDate.get(Calendar.YEAR);
        month = addDate.get(Calendar.MONTH);
        day = addDate.get(Calendar.DAY_OF_MONTH);


        neckEditText = (EditText) findViewById(R.id.edit_neck);
        bustEditText = (EditText) findViewById(R.id.edit_bust);
        chestEditText= (EditText) findViewById(R.id.edit_chest);
        waistEditText = (EditText) findViewById(R.id.edit_waist);
        hipEditText = (EditText) findViewById(R.id.edit_hip);
        inseamEditText = (EditText) findViewById(R.id.edit_inseam);
        commentEditText = (EditText) findViewById(R.id.comment_view);

        doneButton = (Button) findViewById(R.id.Done);

//source: Android - Date Picker
// https://www.tutorialspoint.com/android/android_datepicker_control.html
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddPerson.this, myDateListener,
                        year, day,
                        day).show();
            }
        });


        doneButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (view == doneButton){
                    generateRecord();
                }
            }
        });


    }
//source: Android - Date Picker
// https://www.tutorialspoint.com/android/android_datepicker_control.html
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            addDate.set(Calendar.YEAR, year);
            addDate.set(Calendar.MONTH, monthOfYear);
            addDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateEditText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };


    protected void generateRecord(){
        Intent intent = new Intent(this, MainActivity.class);

        String name = nameEditText.getText().toString();
        Person newPerson = new Person();

        Gson gson = new Gson();


        if (name.isEmpty()){
            nameEditText.setError("Name can't be empty");
        }



        }






}

