package com.example.android.xxiang1_sizebook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.lang.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddPerson extends AppCompatActivity {


    Person person = new Person();

    private ArrayList<Person> PersonList = new ArrayList<>();

    private Calendar addDate = Calendar.getInstance();

    private EditText nameEditText;
    private EditText dateEditText;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
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


        //dateEditText.setHint("Date (Default: " + sdf.format(addDate.getTime()) + ")");

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


        Person newPerson = new Person();

        String name = nameEditText.getText().toString();
        double neck = toDouble(neckEditText.getText().toString());
        double bust = toDouble(neckEditText.getText().toString());
        double chest = toDouble(neckEditText.getText().toString());
        double waist = toDouble(neckEditText.getText().toString());
        double hip = toDouble(neckEditText.getText().toString());
        double inseam = toDouble(neckEditText.getText().toString());
        String comment = neckEditText.getText().toString();


        if (name.isEmpty()){
            nameEditText.setError("Name can't be empty");
        }

        newPerson.setName(name);
        newPerson.setDate(addDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        newPerson.setNeck(neck);
        newPerson.setBust(bust);
        newPerson.setChest(chest);
        newPerson.setWaist(waist);
        newPerson.setHip(hip);
        newPerson.setInseam(inseam);
        newPerson.setComment(comment);

        Intent intent = new Intent();
        intent.putExtra("newPerson", newPerson);
        setResult(MainActivity.RESULT_OK, intent);

        finish();

        }

    //http://stackoverflow.com/questions/6866633/converting-string-to-double-in-android
    //Author: Izkata
    //From 2017-02-03 05:00
    private double toDouble(String s){
        double d = Double.parseDouble(s);
        DecimalFormat df = new DecimalFormat("#.00");
        d = Double.valueOf(df.format(d));
        return d;
    }







}

