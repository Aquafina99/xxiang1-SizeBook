package com.example.android.xxiang1_sizebook;

import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by ceciliaxiang on 2017-02-01.
 */

public class RecordActivity extends AppCompatActivity {

    private ArrayList<Person> recordList = new ArrayList<>();
    private Calendar addedDate;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find the views that we will need to read user input from
        nameEditText= (EditText) findViewById(R.id.name_view);
        dateEditText = (EditText) findViewById(R.id.date_view);
        neckEditText = (EditText) findViewById(R.id.edit_neck);
        bustEditText = (EditText) findViewById(R.id.edit_bust);
        chestEditText= (EditText) findViewById(R.id.edit_chest);
        waistEditText = (EditText) findViewById(R.id.edit_waist);
        hipEditText = (EditText) findViewById(R.id.edit_hip);
        inseamEditText = (EditText) findViewById(R.id.edit_inseam);
        commentEditText = (EditText) findViewById(R.id.comment_view);

        doneButton = (Button) findViewById(R.id.Done);


    }

}
