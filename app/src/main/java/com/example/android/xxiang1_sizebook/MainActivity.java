package com.example.android.xxiang1_sizebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
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

public class MainActivity extends AppCompatActivity {


    private static final String FILENAME = "file.sav";

    private ListView historyList;

    private ArrayList<Person> recordList = new ArrayList<>();
    private ArrayAdapter<Person> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list view for all history record
        historyList = (ListView) findViewById(R.id.record_lists);

        //set up Add Button to open RecordActivity
        Button addNew = (Button) findViewById(R.id.add);
        addNew.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter = new ArrayAdapter<Person>(this, R.layout.activity_main, recordList);
        historyList.setAdapter(adapter);

    }


    @Override
    protected void onResume(){
        super.onResume();
        recordList.clear();

    }




}
